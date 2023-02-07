import React, { Component } from "react";
import { bindActionCreators } from "redux";
import { getGeneration } from "../../redux/actions/Generation/generationAction";
import { getUniversity } from "../../redux/actions/University/universityAction";
import { connect } from "react-redux";
import Swal from "sweetalert2";
import { addUser } from "../../redux/actions/UserControl/usercontrolAction";
import { getCourse } from "../../redux/actions/Course/courseAction";
import Loading from "./../loading/loading";

import localStorage from "../../redux/actions/Login/securityUtils/localStorages";
import { Redirect } from "react-router-dom";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3000,
});
class NewUser extends Component {
  constructor() {
    super();
    this.state = {
      alertMsg: "",
      nameError: "",
      pwdError: "",
      genderError: "",
      roleError: "",
      generationError: "",
      universityError: "",
      isLoading: false,
      updateId: "",
      username: "",
      generation: 0,
      gender: 0,
      password: "",
      role: "",
      roleId: 0,
      university: "",
      user: localStorage(),
    };
  }

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
      nameError = "*Username Cannot Be Blank";
    }
    if (!this.state.password) {
      pwdError = "*Password Cannot Be Blank";
    } else if (this.state.password.length < 8) {
      pwdError = "*Password Cannot less than 8 charaters";
    }
    if (!this.state.gender) {
      genderError = "*Please Select Gender";
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

  handleSubmit = () => {
    let user = {
      username: this.state.username,
      password: this.state.password,
      gender: this.state.gender,
      roleId: this.state.role,
      generationId: this.state.generation,
      universityId: this.state.university,
    };

    if (this.validate()) {
      this.setState({
        isLoading: true,
      });
      addUser(user, (res) => {
        this.props.getGeneration();
        this.props.getCourse();
        this.props.getUniversity();
        this.setState({
          alertIcon: "success",
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
  componentDidMount() {
    this.state.user.role === "SUPER_MENTOR" && this.props.getCourse();
    this.props.getGeneration();
    this.props.getUniversity();
  }
  render() {
    return this.state.isLoading ? (
      <Loading height="70vh" />
    ) : this.state.user.role === "SUPER_MENTOR" ? (
      <div>
        <div
          style={{ marginTop: "50px" }}
          hidden={this.state.isLoading === false ? true : false}
        >
          {this.state.isLoading && <Loading />}
        </div>
        <h3>
          <i height={25} className="fas fa-user mb-2 ml-1"></i> Add New User
        </h3>
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
            <div className="form-group">
              <label>Password</label>
              <input
                onChange={this.handleChange}
                value={this.state.password}
                type="password"
                className="form-control"
                placeholder="Enter Password"
                name="password"
                autoComplete="off"
              />
              <div className="text-danger ">{this.state.pwdError}</div>
            </div>
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
                <option defaultValue={!this.state.generation}>Choose...</option>
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
                <option defaultValue={!this.state.university}>Choose...</option>
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
            <button
              type="submit"
              onClick={this.handleSubmit}
              className="btn btn-primary float-right"
            >
              Submit
            </button>
          </div>
        </div>
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
  };
};
const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getGeneration,
      getUniversity,
      getCourse,
    },
    dispatch
  );
};

export default connect(mapStateToProps, mapDispatchToProps)(NewUser);
