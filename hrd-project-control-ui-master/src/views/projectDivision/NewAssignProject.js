import React, { PureComponent } from "react";
import { bindActionCreators } from "redux";
import { getUser } from "../../redux/actions/UserControl/usercontrolAction";
import {
  assignProject,
  getScheduleByCourseNGen,
} from "../../redux/actions/ProjectDivision/projectdivisionAction";
import { getProjectType } from "../../redux/actions/Schedule/scheduleType/scheduleTypeActions";
import { getProjectOne } from "../../redux/actions/ProjectDivision/Project/ProjectAction";
import { connect } from "react-redux";
import Select from "react-select";
import Swal from "sweetalert2";
import localStorages from "../../redux/actions/Login/securityUtils/localStorages";
import { Redirect } from "react-router-dom";
import Loading from "../loading/loading";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3000,
});

class NewAssignProject extends PureComponent {
  constructor(props) {
    super();
    this.state = {
      isLoading: false,
      selectOptions: [],
      value: [],
      proId: 0,
      type: 0,
      user: localStorages(),
      scheduleError: "",
    };
  }

  dataTable = () => {
    let data = this.props.user.map((datas, i) => {
      return {
        value: datas.userId,
        label: datas.username,
      };
    });
    return data;
  };

  validate = () => {
    let scheduleError = "";
    if (!this.state.type) {
      scheduleError = " * Please Select Schedule Type";
    }
    if (scheduleError) {
      this.setState({
        scheduleError,
      });
      return false;
    }
    return true;
  };

  componentDidMount() {
    const {
      match: { params },
    } = this.props;

    this.props.getUser();
    this.props.getProjectType();
    this.props
      .getProjectOne(params.id)
      .then(() =>
        this.props.getScheduleByCourseNGen(
          this.props.projectOne.course.courseId,
          this.props.projectOne.generation.generationId
        )
      );
    this.setState({
      proId: params.id,
    });
  }

  handleChangeSelect = (e) => {
    this.setState({ value: e });
  };

  handleChange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  handleSubmit = () => {
    let assign_member = this.state.value.map((v) => v.value);
    let member = {
      projectDivisionUserApiRequests: assign_member,
      projectId: this.state.proId,
      scheduleTypeId: this.state.type,
    };

    if (this.validate()) {
      this.setState({
        isLoading: true,
      });
      assignProject(member, (res) => {
        this.setState({
          alertMsg: res.data.message,
        });
        Toast.fire({
          icon: "success",
          title: this.state.alertMsg,
        });
        this.props.history.push("/project_division/assign_project");
      });
    }
  };

  render() {
    return this.state.user.role == "SUPER_MENTOR" ? (
      <>
        <h3>
          {" "}
          <i height={25} className="fab fa-product-hunt mb-2"></i> New Assign
          Project : <b>{this.props.projectOne.name}</b>
        </h3>
        <div>
          {this.state.isLoading ? (
            <Loading height="75vh" />
          ) : (
            <div className="row">
              <div className="col-6">
                <label>Schedule Type</label>
                <select
                  className="custom-select type-select-custom"
                  value={this.state.type}
                  onChange={this.handleChange}
                  name="type"
                >
                  <option defaultValue={!this.state.type}>Choose...</option>
                  {this.props.shceduleFilter.map((datas, i) => (
                    <option key={i} value={datas.id}>
                      {datas.name}
                    </option>
                  ))}
                </select>
                <div className="text-danger ">{this.state.scheduleError}</div>
              </div>
              <div className="col-6">
                <label>
                  Member:
                  <span className="text-danger">
                    * you can both assign mentor and member!
                  </span>
                </label>
                <Select
                  options={this.dataTable()}
                  onChange={this.handleChangeSelect}
                  placeholder="Choose..."
                  isMulti
                  noOptionsMessage={() =>
                    "Student's Name is not available🧑‍🎓❌"
                  }
                />
              </div>
              <div className="col-12 mt-2">
                {this.props.authenticated ? (
                  <button
                    type="submit"
                    onClick={this.handleSubmit}
                    className="btn btn-primary float-right"
                  >
                    Submit
                  </button>
                ) : (
                  <Redirect to="/403"></Redirect>
                )}
              </div>
            </div>
          )}
        </div>
      </>
    ) : (
      <Redirect to="/403"></Redirect>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    user: state.userReducer.user,
    projectType: state.projectTypeReducer.projectType,
    projectOne: state.projectReducer.projectOne,
    authenticated: state.authenticatedReducer.authenticated,
    shceduleFilter: state.projectDivisionReducer.shceduleFilter,
  };
};

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getUser,
      getProjectType,
      assignProject,
      getProjectOne,
      getScheduleByCourseNGen,
    },
    dispatch
  );
};
export default connect(mapStateToProps, mapDispatchToProps)(NewAssignProject);
