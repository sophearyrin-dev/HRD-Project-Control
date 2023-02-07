import React, { Component } from "react";
import {
  updateProject,
  getProjectOne,
  getProjectById,
} from "../../redux/actions/ProjectDivision/Project/ProjectAction";
import { bindActionCreators } from "redux";
import { getCourse } from "../../redux/actions/Course/courseAction";
import { getGeneration } from "../../redux/actions/Generation/generationAction";
import { getProjectDivisionFeatObj } from "../../redux/actions/ProjectDivision/projectdivisionAction";
import Loading from "./../loading/loading";
import { connect } from "react-redux";
import { Form } from "react-bootstrap";
import localStorage from "../../redux/actions/Login/securityUtils/localStorages";
import { Redirect } from "react-router-dom";

import Swal from "sweetalert2";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3000,
});
class UpdateProject extends Component {
  constructor() {
    super();
    this.state = {
      alertMsg: "",
      nameError: "",
      courseError: "",
      generationError: "",
      featureError: "",
      objectiveError: "",
      updateId: "",
      name: "",
      objective: "",
      feature: "",
      generation: 0,
      course: 0,
      projectId: 0,
      isLoading: false,
      user: localStorage(),
    };
  }

  componentDidMount = () => {
    const {
      match: { params },
    } = this.props;
    this.props.getGeneration();
    this.props.getCourse();
    getProjectById(params.id, (datas) => {
      this.setState({
        projectId: params.id,
        feature: datas.feature,
        name: datas.name,
        objective: datas.objective,
        course: datas.course.courseId,
        generation: datas.generation.generationId,
      });
    });
  };

  handleChange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  hideShow() {
    this.setState({ type: !this.state.type });
  }

  validate = () => {
    let nameError = "";
    let courseError = "";
    let generationError = "";
    let objectiveError = "";
    let featureError = "";
    if (!this.state.name) {
      nameError = "*Project Name Cannot Be Blank";
    }
    if (!this.state.generation) {
      generationError = "*Please Select Generation";
    }
    if (!this.state.course) {
      courseError = "* Please Select Course";
    }
    if (!this.state.feature) {
      featureError = "* Project Feature Cannot Be Blank";
    }
    if (!this.state.objective) {
      objectiveError = "* Project Objective Cannot Be Blank";
    }
    if (
      nameError ||
      generationError ||
      courseError ||
      featureError ||
      objectiveError
    ) {
      this.setState({
        nameError,
        generationError,
        courseError,
        featureError,
        objectiveError,
      });
      return false;
    }
    return true;
  };

  handleUpdate = () => {
    this.setState({
      isLoading: true,
    });
    let project = {
      courseId: this.state.course,
      name: this.state.name,
      objective: this.state.objective,
      feature: this.state.feature,
      generationId: this.state.generation,
    };
    if (this.validate()) {
      updateProject(this.state.projectId, project, (res) => {
        this.setState({
          alertIcon: "success",
          alertMsg: res.data.message,
          name: "",
          objective: "",
          feature: "",
          generation: 0,
          course: 0,
        });
        Toast.fire({
          icon: "success",
          title: this.state.alertMsg,
        });
        this.props.history.push("/project_division/project");
      });
    }
  };

  render() {
    return this.state.user.role === "SUPER_MENTOR" ? (
      <div>
        <h3>
          <i height={25} className="fab fa-product-hunt mb-2"></i> Update
          Project
        </h3>

        {this.state.isLoading ? (
          <Loading height="75vh" />
        ) : (
          <div className="row">
            <div className="col-6">
              <div className="form-group">
                <label>Title</label>
                <input
                  onChange={this.handleChange}
                  value={this.state.name}
                  type="text"
                  className="form-control"
                  placeholder="Enter Project Name"
                  name="name"
                  autoComplete="off"
                />
                <div className="text-danger ">{this.state.nameError}</div>
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
                <label>Objective</label>
                <input
                  onChange={this.handleChange}
                  value={this.state.objective}
                  type="text"
                  className="form-control"
                  placeholder="Enter project objective"
                  name="objective"
                  autoComplete="off"
                />
                <div className="text-danger ">{this.state.objectiveError}</div>
              </div>
            </div>

            <div className="col-6">
              <div className="form-group">
                <label>Course</label>
                <select
                  className="custom-select"
                  value={this.state.course}
                  onChange={this.handleChange}
                  name="course"
                >
                  <option defaultValue={!this.state.course}>Choose...</option>
                  {this.props.course.map((datas, i) => (
                    <option key={i} value={datas.courseId}>
                      {datas.name}
                    </option>
                  ))}
                </select>
                <div className="text-danger ">{this.state.courseError}</div>
              </div>
            </div>

            <div className="col-12">
              <Form.Group controlId="exampleForm.ControlTextarea1">
                <Form.Label>Feature</Form.Label>
                <Form.Control
                  as="textarea"
                  rows="7"
                  onChange={this.handleChange}
                  value={this.state.feature}
                  placeholder="Enter project feature"
                  name="feature"
                  autoComplete="off"
                />
              </Form.Group>
              <div className="text-danger ">{this.state.featureError}</div>
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
    authenticated: state.authenticatedReducer.authenticated,
  };
};
const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getCourse,
      getGeneration,
      getProjectOne,
      updateProject,
      getProjectDivisionFeatObj,
    },
    dispatch
  );
};

export default connect(mapStateToProps, mapDispatchToProps)(UpdateProject);
