import React, { Component } from "react";
import { bindActionCreators } from "redux";
import { getCourse } from "../../redux/actions/Course/courseAction";
import { getGeneration } from "../../redux/actions/Generation/generationAction";
import { getUniversity } from "../../redux/actions/University/universityAction";
import { connect } from "react-redux";
import Loading from "./../loading/loading";
import Swal from "sweetalert2";
import {
  getUserById,
  getUserOne,
  editUser,
} from "../../redux/actions/UserControl/usercontrolAction";
import { Redirect } from "react-router";
import localStorage from "../../redux/actions/Login/securityUtils/localStorages";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3000,
});
class UpdateUser extends Component {
  constructor() {
    super();
    this.state = {
      alertMsg: "",
      nameError: "",
      courseError: "",
      generationError: "",
      genderError: "",
      roleError: "",
      pwdError: "",
      password: "",
      updateId: "",
      userId: 0,
      username: "",
      generation: 0,
      gender: 0,
      role: "",
      roleId: 0,
      university: "",
      user: localStorage(),
      type: true,
    };
  }

  componentDidMount = () => {
    const {
      match: { params },
    } = this.props;
    this.props.getGeneration();
    this.props.getUniversity();
    this.props.getCourse();
    getUserById(params.id, (datas) => {
      this.setState({
        userId: params.id,
        username: datas.username,
        password: datas.password,
        gender: datas.gender,
        role: datas.role.roleId,
        generation: datas.generation.generationId,
        university: datas.university.universityId,
      });
    });
  };

  hideShow = () => {
    this.setState({ type: !this.state.type });
  };

  handleChange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  validate = () => {
    let nameError = "";
    let pwdError = "";
    let genderError = "";
    let roleError = "";
    let generationError = "";
    let universityError = "";
    if (!this.state.username) {
      nameError = "* Username Cannot Be Blank";
    }
    if (this.state.password.length < 8) {
      pwdError = "* Password Cannot less than 8 charaters";
    }
    if (!this.state.gender) {
      genderError = "* Please Select Gender";
    }
    if (!this.state.role) {
      roleError = "* Please Select Role";
    }
    if (!this.state.generation) {
      generationError = "*Please Select Generation";
    }
    if (!this.state.university) {
      universityError = "* Please Select University";
    }
    if (
      nameError ||
      pwdError ||
      genderError ||
      roleError ||
      generationError ||
      universityError
    ) {
      this.setState({
        nameError,
        pwdError,
        genderError,
        roleError,
        generationError,
        universityError,
      });
      return false;
    }
    return true;
  };

  handleUpdate = () => {
    this.setState({
      isLoading: true,
    });
    let user = {
      username: this.state.username,
      password: this.state.password,
      gender: this.state.gender,
      roleId: this.state.role,
      generationId: this.state.generation,
      universityId: this.state.university,
    };
    if (this.validate()) {
      editUser(this.state.userId, user, (res) => {
        this.setState({
          alertMsg: res.data.message,
          username: "",
          password: "",
          gender: 0,
          role: 0,
          generation: 0,
          university: 0,
        });
        Toast.fire({
          icon: "success",
          title: this.state.alertMsg,
        });
        this.props.history.push("/user/user");
      });
    }
  };

  render() {
    return this.state.user.role === "SUPER_MENTOR" ? (
      <div>
        <h3>
          <i height={25} className="fab fa-product-hunt mb-2"></i> Update User
        </h3>

        {this.state.isLoading ? (
          <Loading height="75vh" />
        ) : (
          <div className="row">
            <div className="col-6">
              <div className="form-group">
                <label>Name</label>
                <input
                  onChange={this.handleChange}
                  value={this.state.username}
                  type="text"
                  className="form-control"
                  placeholder="Enter Username"
                  name="username"
                  autoComplete="off"
                />
                <div className="text-danger ">{this.state.nameError}</div>
              </div>
            </div>
            <div className="col-6">
              <label>Password</label>

              <div class="input-group mb-3">
                <input
                  type={this.state.type ? "text" : "password"}
                  onChange={this.handleChange}
                  value={this.state.password}
                  className="form-control"
                  placeholder="Enter Password"
                  name="password"
                  autoComplete="off"
                />
                <div class="input-group-append">
                  <span
                    class="input-group-text"
                    id="basic-addon2"
                    onClick={this.hideShow}
                    style={{
                      background: "#2f353a",
                      color: "white",
                      border: "#2f353a",
                    }}
                  >
                    <i
                      class={
                        this.state.type === true
                          ? "fas fa-eye "
                          : "fas fa-eye-slash"
                      }
                      aria-hidden="true"
                    ></i>
                  </span>
                </div>
              </div>
              <div className="text-danger ">{this.state.pwdError}</div>
            </div>

            <div className="col-6">
              <div className="form-group">
                <label>Gender</label>
                <select
                  className="custom-select"
                  value={this.state.gender}
                  onChange={this.handleChange}
                  name="gender"
                >
                  <option defaultValue={!this.state.gender}>Choose...</option>
                  <option value="Male">Male</option>
                  <option value="Female">Female</option>
                </select>

                <div className="text-danger ">{this.state.genderError}</div>
              </div>
            </div>
            <div className="col-6">
              <div className="form-group">
                <label>User Roles</label>
                <select
                  className="custom-select"
                  value={this.state.role}
                  onChange={this.handleChange}
                  name="role"
                >
                  <option defaultValue={!this.state.role}>Choose...</option>
                  <option value="1">Super Mentor</option>
                  <option value="2">Mentor</option>
                  <option value="3">Group Leader</option>
                  <option value="4">Member</option>
                </select>
                <div className="text-danger ">{this.state.roleError}</div>
              </div>
            </div>
            <div className="col-6">
              <div className="form-group">
                <label>Generation</label>
                <select
                  className="custom-select"
                  value={this.state.generation}
                  onChange={this.handleChange}
                  name="generation"
                >
                  <option defaultValue={!this.state.generation}>
                    Choose...
                  </option>
                  {this.props.generation.map((datas, i) => (
                    <option key={i} value={datas.generationId}>
                      {datas.name}
                    </option>
                  ))}
                </select>
                <div className="text-danger ">{this.state.generationError}</div>
              </div>
            </div>

            <div className="col-6">
              <div className="form-group">
                <label>University</label>
                <select
                  className="custom-select"
                  value={this.state.university}
                  onChange={this.handleChange}
                  name="university"
                >
                  <option defaultValue={!this.state.university}>
                    Choose...
                  </option>
                  {this.props.university.map((datas, i) => (
                    <option key={i} value={datas.universityId}>
                      {datas.name}
                    </option>
                  ))}
                </select>
                <div className="text-danger ">{this.state.universityError}</div>
              </div>
            </div>
            <div className="col-12">
              {this.props.authenticated ? (
                <button
                  type="submit"
                  onClick={this.handleUpdate}
                  className="btn btn-warning float-right text-center"
                >
                  Update
                </button>
              ) : (
                <Redirect to="/403"></Redirect>
              )}
            </div>
          </div>
        )}
      </div>
    ) : (
      <Redirect to="/403"></Redirect>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    course: state.courseReducer.course,
    generation: state.generationReducer.generation,
    university: state.universityReducer.university,
    authenticated: state.authenticatedReducer.authenticated,
  };
};
const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getCourse,
      getUniversity,
      getGeneration,
      editUser,
      getUserOne,
    },
    dispatch
  );
};

export default connect(mapStateToProps, mapDispatchToProps)(UpdateUser);
