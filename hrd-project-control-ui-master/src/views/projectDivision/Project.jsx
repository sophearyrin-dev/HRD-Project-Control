import React, { Component } from "react";
import { getCourse } from "../../redux/actions/Course/courseAction";
import { getGeneration } from "../../redux/actions/Generation/generationAction";
import {
  getProject,
  deleteProject,
} from "../../redux/actions/ProjectDivision/Project/ProjectAction";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import { Link, Redirect } from "react-router-dom";
import Loading from "./../loading/loading";
//data table
import { MDBDataTable } from "mdbreact";

import localStorage from "../../redux/actions/Login/securityUtils/localStorages";

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
      isLoading: true,
      user: localStorage(),
    };
  }

  handleChange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  componentDidMount() {
    this.state.user.role === "SUPER_MENTOR" && this.props.getGeneration();
    this.props.getCourse();
    this.props.getProject().then(() =>
      this.setState({
        isLoading: false,
      })
    );
  }

  dataTable = () => {
    let data = this.props.project.map((datas, i) => {
      return {
        id: i + 1,
        name: datas.name,
        nameGen: datas.generation.name,
        nameCou: datas.course.name,
        view: (
          <div className="text-center">
            <Link to={`/project_division/update_project/${datas.projectId}`}>
              <button
                type="button"
                className="btn btn-primary btn-sm text-white"
              >
                <i className="fas fa-edit "></i>{" "}
              </button>
            </Link>
            <button
              onClick={() => {
                this.props.deleteProject(datas.projectId);
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

  render() {
    const data = {
      columns: [
        {
          label: "#",
          field: "id", //field for data
          sort: "asc",
        },
        {
          label: "Project Name",
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

    if (this.props.project.length < 1) {
      return <Loading height="75vh" />;
    } else {
      return this.state.user.role === "SUPER_MENTOR" ? (
        <div>
          <h3>
            <i height={25} className="fab fa-product-hunt mb-2"></i> New Project
          </h3>
          <Link
            type="submit"
            className="btn btn-primary float-right"
            to="/project_division/new_project"
          >
            Add New
          </Link>
          <br />
          <br />
          <MDBDataTable striped bordered hover data={data} btn />
        </div>
      ) : (
        <Redirect to="/403"></Redirect>
      );
    }
  }
}

const mapStateToProps = (state) => {
  return {
    project: state.projectReducer.project,
    course: state.courseReducer.course,
    generation: state.generationReducer.generation,
  };
};
const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getProject,
      deleteProject,
      getCourse,
      getGeneration,
    },
    dispatch
  );
};

export default connect(mapStateToProps, mapDispatchToProps)(NewScheduleType);
