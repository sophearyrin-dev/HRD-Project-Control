import React, { PureComponent } from "react";
import "./Task.css";
import { Link } from "react-router-dom";
import { bindActionCreators } from "redux";
import { getCourse } from "../../redux/actions/Course/courseAction";
import { getGeneration } from "../../redux/actions/Generation/generationAction";
import { connect } from "react-redux";
import { getTask } from "../../redux/actions/Task/taskAction";
import Loading from "./../loading/loading";
import { ProgressBar } from "react-bootstrap";
import {
  getUserProject,
  getUserIssue,
  getAllIssues,
} from "../../redux/actions/Dashboard/dashboardAction";
import localStorage from "../../redux/actions/Login/securityUtils/localStorages";

class Task extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      generation: 0,
      course: 0,
      isLoading: false,
      user: localStorage(),
      shows: false,
      empty: true,
      loadings: true,
      isNulls: true,
    };
  }

  showSuperMentor() {
    this.props.getTask();
    this.props.getCourse();
    this.props.getGeneration().then(() =>
      this.setState({
        isLoading: false,
      })
    );
  }
  componentDidMount() {
    this.state.user.role == "SUPER_MENTOR" || this.state.user.role == "MENTOR"
      ? this.showSuperMentor()
      : this.props.getUserProject(this.state.user.id).then(() => {
          this.setState({ isLoading: false });
          this.state.isLoading !== true &&
            this.props.userProject.map((data) =>
              this.props.checkProject
                ? data.courseId == 2 &&
                  this.props
                    .getUserIssue(data.projectId)
                    .then(() => this.setState({ loadings: false, shows: true }))
                : data.courseId == 1 &&
                  this.props
                    .getUserIssue(data.projectId)
                    .then(() => this.setState({ loadings: false, shows: true }))
            );
        });
  }

  handleChange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
    this.props.getCourse();
  };

  handleFilter = () => {
    this.setState({
      isLoading: true,
      isNulls: false,
    });

    this.props.getTask(this.state.course, this.state.generation).then(() =>
      this.setState({
        generation: 0,
        course: 0,
        isLoading: false,
        empty: false,
      })
    );
  };

  render() {
    return (
      <>
        <h3>
          {" "}
          <i className="fas fa-tasks"></i> Task Managment
        </h3>
        <div>
          <div style={{ marginBottom: "20px" }}>
            {this.state.user.role === "SUPER_MENTOR" ||
            this.state.user.role === "MENTOR" ? (
              <div>
                {" "}
                <div className="form-row float-right">
                  <div className="col-auto ">
                    <label>Generation</label>
                    <select
                      className="custom-select shadow"
                      value={this.state.generation}
                      onChange={this.handleChange}
                      name="generation"
                    >
                      <option defaultValue={this.state.generation}>
                        Choose...
                      </option>
                      {this.props.generation.map((datas, i) => (
                        <option key={i} value={datas.generationId}>
                          {datas.name}
                        </option>
                      ))}
                    </select>
                  </div>
                  <div className="col-auto">
                    <label>Course</label>
                    <select
                      className="custom-select shadow"
                      value={this.state.course}
                      onChange={this.handleChange}
                      name="course"
                    >
                      <option defaultValue={this.state.course}>
                        Choose...
                      </option>
                      {this.props.course.map((datas, i) => (
                        <option key={i} value={datas.courseId}>
                          {datas.name}
                        </option>
                      ))}
                    </select>
                  </div>
                  <div className="col-auto" style={{ marginTop: "30px" }}>
                    <button
                      type="filter"
                      onClick={this.handleFilter}
                      className="btn btn-primary shadow"
                    >
                      Filter
                    </button>
                  </div>
                </div>
              </div>
            ) : (
              <>
                <span></span>
              </>
            )}
            <div className="clearfix"> </div>
          </div>

          {this.state.user.role == "SUPER_MENTOR" ||
          this.state.user.role == "MENTOR" ? (
            <>
              {this.state.isLoading && <Loading height="60vh" />}

              <div className="row">
                {this.props.task < 1 ? (
                  <>
                    {this.state.isNulls && (
                      <div
                        className="col-12"
                        style={{
                          left: "48%",
                          top: "55%",
                          position: "absolute",
                        }}
                      >
                        <h4>Please Filter To Get Data</h4>
                      </div>
                    )}
                    <div
                      className="col-12"
                      style={{
                        textAlign: "center",
                        marginTop: "10%",
                      }}
                      hidden={this.state.empty}
                    >
                      <h4>
                        There's No Project task In This Generation or Course
                      </h4>
                    </div>
                  </>
                ) : (
                  <>
                    {this.state.isLoading !== true ? (
                      this.props.task.map((datas, i) => (
                        <div className="col-3 " key={i}>
                          <div
                            className="bck-card "
                            style={{ padding: "0 15px" }}
                          >
                            <div className="row ">
                              <div className="col-3">
                                <div className="rounded-circle text-center p-2 round-name">
                                  <strong className="text-white">
                                    {datas.projectName.charAt(0)}
                                  </strong>
                                </div>
                              </div>
                              <div
                                className="col-9  text-truncate"
                                title=""
                                style={{ paddingTop: "1rem " }}
                              >
                                <strong className="float-right">
                                  <h5 style={{ color: "#3c4b64" }}>
                                    {" "}
                                    {datas.projectName}
                                  </h5>
                                </strong>
                                <div className="clearfix"></div>
                                <div className="mt-1">
                                  <strong className="float-right text-warning">
                                    ISSUE:{" "}
                                    <span className="badge-warning rounded t-badge text-white">
                                      {datas.numberOfIssue}
                                    </span>
                                  </strong>
                                </div>
                              </div>
                              <div className="col-12 mt-3">
                                <ProgressBar
                                  variant="success"
                                  now={datas.projectProgress}
                                  label={`${datas.projectProgress}%`}
                                />
                              </div>
                              <div className="col-12">
                                <br />
                                <hr
                                  className="m-0"
                                  style={{ background: "white" }}
                                />
                                <Link
                                  to={`/task/view_task/${datas.projectId}`}
                                  className="float-right m-3 text-black-50 text-decoration-none"
                                  style={{ color: "#3c4b64" }}
                                >
                                  View{" "}
                                  <i
                                    className="fas fa-chevron-right    "
                                    style={{ color: "#3c4b64" }}
                                  ></i>
                                </Link>
                              </div>
                            </div>
                          </div>
                        </div>
                      ))
                    ) : (
                      <span></span>
                    )}
                  </>
                )}
              </div>
            </>
          ) : (
            <>
              {this.state.loadings && <Loading height="60vh" />}
              {this.state.loadings !== true ? (
                this.props.userProject.map((data, i) =>
                  this.props.checkProject
                    ? data.courseId == 2 && (
                        <>
                          {this.state.shows ? (
                            <div className="col-3 " key={i}>
                              <div
                                className="bck-card "
                                style={{ padding: "0 15px" }}
                              >
                                <div className="row ">
                                  <div className="col-3">
                                    <div className="rounded-circle text-center p-2 round-name">
                                      <strong className="text-white">
                                        {data.name.charAt(0)}
                                      </strong>
                                    </div>
                                  </div>
                                  <div
                                    className="col-9  text-truncate"
                                    title={data.projectName}
                                    style={{ paddingTop: "1rem " }}
                                  >
                                    <strong className="float-right">
                                      <h5 style={{ color: "#3c4b64" }}>
                                        {" "}
                                        {data.projectName}
                                      </h5>
                                    </strong>
                                    <div className="clearfix"></div>
                                    <div className="mt-1">
                                      <strong className="float-right  text-warning">
                                        ISSUE:{" "}
                                        <span className="badge-warning t-badge rounded text-white">
                                          {this.props.userIssue[0]}
                                        </span>
                                      </strong>
                                    </div>
                                  </div>
                                  <div className="col-12 mt-3">
                                    <ProgressBar
                                      variant="success"
                                      now={this.props.userIssue[1]}
                                      label={`${this.props.userIssue[1]}%`}
                                    />
                                  </div>
                                  <div className="col-12">
                                    <br />
                                    <hr
                                      className="m-0"
                                      style={{ background: "white" }}
                                    />
                                    <Link
                                      to={`/task/view_task/${data.projectId}`}
                                      className="float-right m-2  text-decoration-none"
                                      style={{ color: "#3c4b64" }}
                                    >
                                      View{" "}
                                      <i
                                        className="fas fa-chevron-right    "
                                        style={{ color: "#3c4b64" }}
                                      ></i>
                                    </Link>
                                  </div>
                                </div>
                              </div>
                            </div>
                          ) : (
                            <Loading height="60vh" />
                          )}
                        </>
                      )
                    : data.courseId == 1 && (
                        <>
                          {this.state.shows ? (
                            <div className="col-3 " key={i}>
                              <div
                                className="bck-card "
                                style={{ padding: "0 15px" }}
                              >
                                <div className="row ">
                                  <div className="col-3">
                                    <div className="rounded-circle t-badge text-center p-2 round-name">
                                      <strong className="text-white">
                                        {data.name.charAt(0)}
                                      </strong>
                                    </div>
                                  </div>
                                  <div
                                    className="col-9  text-truncate"
                                    title=""
                                    style={{ paddingTop: "1rem " }}
                                  >
                                    <strong className="float-right">
                                      <h5 style={{ color: "#3c4b64" }}>
                                        {" "}
                                        {data.name}
                                      </h5>
                                    </strong>
                                    <div className="clearfix"></div>
                                    <div className="mt-1">
                                      <strong className="float-right  text-warning">
                                        ISSUE:{" "}
                                        <span className="badge-warning t-badge rounded text-white">
                                          {this.props.userIssue[0]}
                                        </span>
                                      </strong>
                                    </div>
                                  </div>
                                  <div className="col-12 mt-3">
                                    <ProgressBar
                                      variant="success"
                                      now={this.props.userIssue[1]}
                                      label={`${this.props.userIssue[1]}%`}
                                    />
                                  </div>
                                  <div className="col-12">
                                    <br />
                                    <hr
                                      className="m-0"
                                      style={{ background: "white" }}
                                    />
                                    <Link
                                      to={`/task/view_task/${data.projectId}`}
                                      className="float-right m-3 text-decoration-none"
                                      style={{ color: "#3c4b64" }}
                                    >
                                      View{" "}
                                      <i
                                        className="fas fa-chevron-right    "
                                        style={{ color: "#3c4b64" }}
                                      ></i>
                                    </Link>
                                  </div>
                                </div>
                              </div>
                            </div>
                          ) : (
                            <Loading height="60vh" />
                          )}
                        </>
                      )
                )
              ) : (
                <span></span>
              )}
            </>
          )}
        </div>
      </>
    );
  }
}
const mapStateToProps = (state) => {
  return {
    course: state.courseReducer.course,
    generation: state.generationReducer.generation,
    task: state.taskReducer.task,
    userProject: state.dashboardReducer.userProject,
    checkProject: state.dashboardReducer.check,
    userIssue: state.dashboardReducer.userIssue,
    projectIssue: state.dashboardReducer.projectIssue,
  };
};

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getCourse,
      getGeneration,
      getTask,
      getUserProject,
      getUserIssue,
      getAllIssues,
    },
    dispatch
  );
};
export default connect(mapStateToProps, mapDispatchToProps)(Task);
