import React, { Component } from "react";
import { userLogin } from "../../redux/actions/Login/loginAction";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import {
  CButton,
  CCard,
  CCardBody,
  CCardGroup,
  CCol,
  CContainer,
  CForm,
  CInput,
  CInputGroup,
  CInputGroupPrepend,
  CInputGroupText,
  CRow,
} from "@coreui/react";

import Particles from "react-particles-js";
import CIcon from "@coreui/icons-react";
import logo_login from "../../img/login-logo.png";
import "./Login.css";
import Loader from "react-loader-spinner";
class Login extends Component {
  constructor() {
    super();
    this.state = {
      username: "",
      password: "",
      usernameError: "",
      passwordError: "",
      type: true,
      visible: false,
      loading: false,
    };

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  componentDidMount() {
    this.props.userLogin();
    if (this.props.security) {
      this.props.history.push("/dashboard");
    }
  }

  validate = () => {
    let usernameError = "";
    let passwordError = "";

    if (!this.state.username) {
      usernameError = " * Username cannot be blank";
    }

    if (this.state.password.length < 8) {
      if (!this.state.password) passwordError = "* Password cannot be blank";
      else passwordError = "* Password Cannot less than 8 charaters";
    }

    if (passwordError || usernameError) {
      this.setState({ passwordError, usernameError, loading: false });
      return false;
    }
    return true;
  };

  onSubmit(e) {
    this.setState({
      visible: true,
      loading: true,
    });
    setTimeout(() => {
      this.setState({
        visible: false,
        usernameError: "",
        passwordError: "",
      });
    }, 5000);

    e.preventDefault();
    const invalid = this.validate();
    const LoginRequest = {
      username: this.state.username,
      password: this.state.password,
    };

    if (invalid) {
      this.props.userLogin(LoginRequest).then(() => {
        if (this.props.users !== undefined) {
          this.props.history.push("/dashboard");
        } else {
          this.setState({
            loading: false,
            passwordError: "your username and password invalid",
          });
        }
      });
    }
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  handleKeypress = (e) => {
    //it triggers by pressing the enter key
    if (e.keyCode === 13) {
      this.onSubmit(e);
    }
  };

  hideShow() {
    this.setState({ type: !this.state.type });
  }

  render() {
    return (
      <>
        <div
          hidden={this.state.visible === false ? true : false}
          className="gg m-2 float-right"
          style={{ position: "absolute", zIndex: "5", left: "85%" }}
        >
          <div
            hidden={this.state.usernameError === "" ? true : false}
            id="alert "
            className="alert alert-danger "
          >
            <i className="fas fa-times-circle    "></i>
            <strong>{this.state.usernameError}</strong>
          </div>
          <div
            id="alert "
            className="alert alert-danger "
            hidden={this.state.passwordError === "" ? true : false}
          >
            <i className="fas fa-times-circle    "></i>
            <strong>{this.state.passwordError}</strong>
          </div>
        </div>
        <Particles
          id="particles"
          params={{
            particles: {
              color: {
                value: "#3c4b64",
              },
              number: {
                value: 180,
                density: {
                  enable: false,
                },
              },
              size: {
                value: 3,
                random: true,
                anim: {
                  speed: 4,
                  size_min: 0.3,
                },
              },
              line_linked: {
                enable: false,
              },
              move: {
                random: true,
                speed: 1,
                direction: "top",
                out_mode: "out",
              },
            },
            interactivity: {
              events: {
                onhover: {
                  enable: true,
                  mode: "bubble",
                },
                onclick: {
                  enable: true,
                  mode: "repulse",
                },
              },
              modes: {
                bubble: {
                  distance: 250,
                  duration: 2,
                  size: 0,
                  opacity: 0,
                },
                repulse: {
                  distance: 400,
                  duration: 4,
                },
              },
            },
          }}
        />
        <div className="c-app c-default-layout flex-row align-items-center gg">
          <CContainer>
            <CRow className="justify-content-center">
              <CCol md="8">
                <CCardGroup>
                  <CCard className="p-4">
                    <CCardBody>
                      <CForm>
                        <h1>Login</h1>
                        <p className="text-muted">Sign In to your account</p>
                        <CInputGroup className="mb-3">
                          <CInputGroupPrepend>
                            <CInputGroupText
                              style={{ background: "#2f353a", color: "white" }}
                            >
                              <CIcon name="cil-user" />
                            </CInputGroupText>
                          </CInputGroupPrepend>
                          <CInput
                            type="text"
                            placeholder="Username"
                            autoComplete="username"
                            name="username"
                            value={this.props.username}
                            onChange={this.onChange}
                            onKeyPress={this.handleKeypress}
                          />
                        </CInputGroup>

                        <CInputGroup className="mb-4">
                          <CInputGroupPrepend>
                            <CInputGroupText
                              style={{ background: "#2f353a", color: "white" }}
                            >
                              <CIcon name="cil-lock-locked" />
                            </CInputGroupText>
                          </CInputGroupPrepend>
                          <CInput
                            type={this.state.type ? "password" : "text"}
                            placeholder="Password"
                            autoComplete="current-password"
                            name="password"
                            value={this.props.password}
                            onChange={this.onChange}
                            onKeyPress={this.handleKeypress}
                          />
                          <div className="input-group-append">
                            <span
                              className="input-group-text"
                              style={{ background: "#2f353a", color: "white" }}
                              onClick={this.hideShow.bind(this)}
                            >
                              <i
                                className={
                                  this.state.type === true
                                    ? "fas fa-eye "
                                    : "fas fa-eye-slash"
                                }
                              ></i>
                            </span>
                          </div>
                        </CInputGroup>

                        <CRow>
                          <CCol xs="4">
                            <CButton
                              type="submit"
                              onClick={this.onSubmit}
                              style={{ background: "#2f353a", color: "white" }}
                              className="px-4"
                            >
                              Login
                            </CButton>
                          </CCol>
                          {this.state.loading && (
                            <Loader
                              type="Oval"
                              color="#2f353a"
                              height={35}
                              width={35}
                            />
                          )}
                        </CRow>
                      </CForm>
                    </CCardBody>
                  </CCard>
                  <CCard
                    className="text-white py-5 d-md-down-none"
                    style={{ width: "44%", background: "#2f353a" }}
                  >
                    <CCardBody className="text-center">
                      <div>
                        <img src={logo_login} height={170} alt="loginlogo" />
                      </div>
                    </CCardBody>
                  </CCard>
                </CCardGroup>
              </CCol>
            </CRow>
          </CContainer>
        </div>
      </>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    security: state.loginReducer.validToken,
    users: state.loginReducer.users,
  };
};

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      userLogin,
    },
    dispatch
  );
};

export default connect(mapStateToProps, mapDispatchToProps)(Login);
