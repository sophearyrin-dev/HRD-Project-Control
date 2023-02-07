import React, { Component } from "react";
import {
  getProjectType,
  addScheduleType,
  deleteScheduleType,
  updateScheduleType,
} from "../../redux/actions/Schedule/scheduleType/scheduleTypeActions.js";
import { getCourse } from "../../redux/actions/Course/courseAction";
import { getGeneration } from "../../redux/actions/Generation/generationAction";
import Swal from "sweetalert2";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import "./Schedule.css";
import Loading from "../loading/loading";
import { Redirect } from "react-router-dom";
import localStorage from "../../redux/actions/Login/securityUtils/localStorages";

//new dbTAbleaa
import { MDBDataTable } from "mdbreact";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3000,
});
class NewScheduleType extends Component {
  constructor(props) {
    super();
    this.state = {
      alertIcon: "",
      alertMsg: "",
      nameError: "",
      courseError: "",
      generationError: "",
      isUpdate: false,
      updateId: "",
      name: "",
      generation: 0,
      course: 0,
      isLoading: true,
      tableRows: [], //for new tbl
      user: localStorage(),
    };
  }

  // arrow function for loop dta new tbl
  dataTable = () => {
    let data = this.props.projectType.map((datas, i) => {
      return {
        id: i + 1,
        name: datas.name, //name is a field name
        nameGen: datas.generation.name,
        nameCou: datas.course.name,
        view: (
          <div className="text-center">
            <button
              type="button"
              className="mx-1 btn btn-sm btn-primary btn-md"
              onClick={() =>
                this.handleEdit(
                  datas.typeId,
                  datas.name,
                  datas.course.courseId,
                  datas.generation.generationId
                )
              }
            >
              <i className="fas fa-edit "></i>{" "}
            </button>
            <button
              onClick={() => {
                this.props.deleteScheduleType(datas.typeId);
              }}
              type="button"
              className="mx-1 btn btn-sm btn-danger btn-md"
            >
              {" "}
              <i className="fas fa-trash-alt    "></i>
            </button>
          </div>
        ),
      };
    });
    return data;
  };

  handleChange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  validate = () => {
    let nameError = "";
    let courseError = "";
    let generationError = "";
    if (!this.state.name) {
      nameError = "*Schedule Type Cannot Be Blank";
    }
    if (!this.state.generation) {
      generationError = "*Please Select Generation";
    }
    if (!this.state.course) {
      courseError = "* Please Select Course";
    }
    if (nameError || generationError || courseError) {
      this.setState({
        nameError,
        generationError,
        courseError,
      });
      return false;
    }
    return true;
  };

  handleSubmit = () => {
    const category = {
      name: this.state.name,
      generationId: this.state.generation,
      courseId: this.state.course,
    };
    if (this.validate()) {
      if (this.state.isUpdate) {
        updateScheduleType(this.state.updateId, category, (res) => {
          this.props.getProjectType();
          this.props.getGeneration();
          this.props.getCourse();
          this.setState({
            alertMsg: res.data.message,
            isUpdate: false,
            name: "",
            generation: 0,
            course: 0,
            updateId: "",
          });
          Toast.fire({
            icon: "success",
            title: this.state.alertMsg,
          });
        });
      } else {
        addScheduleType(category, (res) => {
          this.props.getProjectType();
          this.props.getGeneration();
          this.props.getCourse();
          this.setState({
            alertIcon: "success",
            alertMsg: res.data.message,
            name: "",
            generation: 0,
            course: 0,
          });
          Toast.fire({
            icon: "success",
            title: this.state.alertMsg,
          });
        });
      }
      this.setState({ nameError: "", courseError: "", generationError: "" });
    }
  };
  componentDidMount() {
    this.state.user.role === "SUPER_MENTOR" &&
      this.props.getProjectType().then(() =>
        this.setState({
          // isLoading: false,
        })
      );
    this.props.getGeneration();
    this.props.getCourse();
  }
  handleEdit = (typeId, name, courseId, generationId) => {
    this.setState({
      updateId: typeId,
      name: name,
      course: courseId,
      generation: generationId,
      isUpdate: true,
    });
  };

  render() {
    const data = {
      columns: [
        {
          label: "#",
          field: "id", //field for data
          sort: "asc",
        },
        {
          label: "Schedule Type",
          field: "name",
          sort: "asc",
        },
        {
          label: "Generation",
          field: "nameGen",
          sort: "asc",
        },
        {
          label: "Course",
          field: "nameCou",
          sort: "asc",
        },
        {
          label: "Action",
          field: "view",
          sort: "asc",
        },
      ],

      rows: this.dataTable(),
    };

    if (this.props.projectType.length === 0) {
      return <Loading height="84vh" />;
    } else {
      return this.state.user.role === "SUPER_MENTOR" ? (
        <div>
          <h3>
            <i className="fas fa-calendar-alt    "></i> New Schedule Type
          </h3>
          <div>
            <div className="form-row mt-3">
              <div className="col-6">
                <div className="form-group">
                  <label>Schedule Type</label>
                  <input
                    onChange={this.handleChange}
                    value={this.state.name}
                    type="text"
                    className="form-control"
                    placeholder="Enter Schedule Type"
                    name="name"
                  />
                  <div className="text-danger ">{this.state.nameError}</div>
                </div>
              </div>
              <div className="col-auto ">
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
                <div className="text-danger">{this.state.generationError}</div>
              </div>
              <div className="col-auto">
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
              <div className="col-auto" style={{ marginTop: "30px" }}>
                <button
                  type="submit"
                  onClick={this.handleSubmit}
                  className={
                    this.state.updateId ? "btn btn-warning" : "btn btn-primary"
                  }
                >
                  {this.state.updateId ? "Update" : "Submit"}
                </button>
              </div>
            </div>
          </div>
          <div className="clearfix"> </div>
          <MDBDataTable striped bordered hover data={data} btn />
        </div>
      ) : (
        <Redirect to="/404"></Redirect>
      );
    }
  }
}

const mapStateToProps = (state) => {
  return {
    projectType: state.projectTypeReducer.projectType,
    course: state.courseReducer.course,
    generation: state.generationReducer.generation,
  };
};
const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getProjectType,
      getCourse,
      getGeneration,
      deleteScheduleType,
    },
    dispatch
  );
};

export default connect(mapStateToProps, mapDispatchToProps)(NewScheduleType);
