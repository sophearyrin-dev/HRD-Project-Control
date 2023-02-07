import React, { Component } from "react";
import localStorage from "../../redux/actions/Login/securityUtils/localStorages";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import "./Dashboard.css";
import {
  getUserProject,
  getUserIssue,
  getAllProject,
  getAllIssues,
} from "../../redux/actions/Dashboard/dashboardAction";
import { Card, Modal, Button, Alert } from "react-bootstrap";
import Loading from "./../loading/loading";
import { getCourse } from "../../redux/actions/Course/courseAction";
import { getGeneration } from "../../redux/actions/Generation/generationAction";
import Loader from "react-loader-spinner";
import ProgressBar from "react-bootstrap/ProgressBar";
import { Link } from "react-router-dom";

class Dashboard extends Component {
  constructor(props) {
    super(props);
    this.state = {
      user: localStorage(),
      shows: false,
      generation: 0,
      course: 0,
      generationId: "",
      courseId: "",
      expandedRows: null,
      isLoading: false,
      isLoadingModal: false,
      show: false,
      empty: true,
      isNull: true,
      loadings: true,
    };
  }
  showSuperMentor() {
    this.props.getGeneration();
    this.props.getCourse();
  }

  componentDidMount() {
    this.state.user.role == "SUPER_MENTOR" || this.state.user.role == "MENTOR"
      ? this.showSuperMentor()
      : this.props.getUserProject(this.state.user.id).then(() => {
          this.setState({ loadings: false });
          this.state.loadings !== true &&
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

  handleFilter = () => {
    this.setState({
      isLoading: true,
      isNull: false,
    });

    this.props
      .getAllProject(this.state.course, this.state.generation)
      .then(() =>
        this.setState({
          generation: 0,
          course: 0,
          isLoading: false,
          isNull: false,
          empty: false,
        })
      );
  };
  handleChange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };
  handleClose = () => {
    this.setState({ show: false });
    this.setState({ expandedRows: null });
    this.setState({
      isLoadingModal: false,
    });
  };

  showIssue = (proId) => {
    this.setState({
      expandedRows: proId,
      show: true,
    });
    this.props
      .getAllIssues(proId)
      .then(() => this.setState({ isLoadingModal: true }));
  };

  render() {
    return (
      <>
        <Modal show={this.state.show} onHide={this.handleClose} size="md">
          <Modal.Header closeButton>
            <Modal.Title>
              <h5>
                {" "}
                <i height={25} className="fas fa-clipboard-list"></i> ALL
                PROJECT ISSUES
              </h5>
            </Modal.Title>
          </Modal.Header>
          <Modal.Body className="height_model">
            {!this.state.isLoadingModal && (
              <Loader
                type="Bars"
                color="#2f353a"
                className="loaders"
                height={50}
                width={50}
              />
            )}
            {this.state.isLoadingModal &&
              this.props.projectIssue.map((data, i) => (
                <Alert key={i} variant="danger">
                  {data.handler} :{" "}
                  <span className="text-danger">{data.issue}.</span>
                </Alert>
              ))}
          </Modal.Body>
          <Modal.Footer>
            <Button
              variant="danger"
              className="btn-sm"
              onClick={this.handleClose}
            >
              Close
            </Button>
          </Modal.Footer>
        </Modal>
        <h3>
          {" "}
          <i className="fas fa-tachometer-alt"></i> Dashboard
        </h3>
        <div>
          {this.state.user.role === "SUPER_MENTOR" ||
          this.state.user.role == "MENTOR" ? (
            <>
              <div style={{ marginBottom: "20px" }}>
                <div>
                  {" "}
                  <div className="form-row float-right">
                    <div className="col-auto ">
                      <label>Generation</label>
                      <select
                        className="custom-select"
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
                        className="custom-select"
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
                        className="btn btn-primary"
                      >
                        Filter
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </>
          ) : (
            <>
              <span></span>
            </>
          )}
        </div>
        <div className="clearfix"></div>

        <br />
        <br />
        {this.state.user.role == "SUPER_MENTOR" ||
        this.state.user.role == "MENTOR" ? (
          <>
            {this.state.isLoading && <Loading height="60vh" />}
            <div className="row-eq-height row pt-3">
              {this.props.projectAll.length < 1 ? (
                <>
                  {this.state.isNull && (
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
                    <h4>There's No Project In This Generation or Course</h4>
                  </div>
                </>
              ) : (
                <>
                  {this.state.isLoading !== true ? (
                    this.props.projectAll.map((data, i) => (
                      <div className="col-sm-12 col-md-6 col-xl-4 " key={i}>
                        <div className="top_header">
                          <p>{data.projectName.charAt(0).toUpperCase()}</p>
                        </div>
                        <div className="card rounded-5 gr-bg p-3 box_container text-decoration-none">
                          <div className="header_box">
                            <h6>
                              <b>{data.projectName}</b>
                            </h6>
                            <p className="issues text-warning">
                              Issues:{" "}
                              <span
                                className={
                                  data.numberOfIssue < 1
                                    ? "badge-warning rounded t-badge text-white test"
                                    : "badge-warning rounded t-badge text-white"
                                }
                                onClick={() => this.showIssue(data.projectId)}
                              >
                                {data.numberOfIssue}
                              </span>
                            </p>
                          </div>
                          <div className="box_body">
                            <p className="course">{data.courseName}</p>
                            <p className="generation">{data.generationName}</p>
                          </div>
                          <div>
                            <ProgressBar
                              variant="success"
                              now={data.projectProgress}
                              label={`${data.projectProgress}%`}
                            />
                          </div>
                          <hr className="m-0 mt-2" />
                          <Link
                            style={{ marginBottom: "-7px" }}
                            className="ml-auto mt-2 text-decoration-none"
                            to={`/task/view_task/${data.projectId}`}
                          >
                            More{" "}
                            <i
                              className="fa fa-chevron-right"
                              aria-hidden="true"
                            ></i>
                          </Link>
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
                      <div className="row">
                        {this.state.shows ? (
                          <div className="col-sm-12 col-md-6 col-xl-4" key={i}>
                            <div className="top_header rounded-5 ">
                              <p>{data.name.charAt(0).toUpperCase()}</p>
                            </div>
                            <div className="card rounded-5 gr-bg p-3 box_container">
                              <div className="header_box">
                                <h6>
                                  <b>{data.name}</b>
                                </h6>
                                <p className="issues text-warning">
                                  Issues:{" "}
                                  <span
                                    className={
                                      this.props.userIssue[0] < 1
                                        ? "badge-warning rounded t-badge text-white test"
                                        : "badge-warning rounded t-badge text-white"
                                    }
                                    onClick={() =>
                                      this.showIssue(data.projectId)
                                    }
                                  >
                                    {this.props.userIssue[0]}
                                  </span>
                                </p>
                              </div>
                              <div className="box_body">
                                <p className="course">{data.course}</p>
                                <p className="generation">{data.generation}</p>
                              </div>
                              <div>
                                <ProgressBar
                                  variant="success"
                                  now={this.props.userIssue[1]}
                                  label={`${this.props.userIssue[1]}%`}
                                />
                                <hr className="m-0 mt-2" />
                                <Link
                                  style={{ marginBottom: "-7px" }}
                                  className="ml-auto mt-2 text-decoration-none"
                                  to={`/task/view_task/${data.projectId}`}
                                >
                                  More{" "}
                                  <i
                                    className="fa fa-chevron-right"
                                    aria-hidden="true"
                                  ></i>
                                </Link>
                              </div>
                            </div>
                          </div>
                        ) : (
                          <Loading height="60vh" />
                        )}
                      </div>
                    )
                  : data.courseId == 1 && (
                      <>
                        {this.state.shows ? (
                          <div className="row">
                            <div
                              className="col-sm-12 col-md-6 col-xl-4"
                              key={i}
                            >
                              <div className="top_header rounded-5 ">
                                <p>{data.name.charAt(0).toUpperCase()}</p>
                              </div>
                              <div className="card rounded-5 gr-bg p-3 box_container">
                                <div className="header_box">
                                  <h6>
                                    <b>{data.name}</b>
                                  </h6>
                                  <p className="issues text-warning">
                                    Issues:{" "}
                                    <span
                                      className={
                                        this.props.userIssue[0] < 1
                                          ? "badge-warning rounded t-badge text-white test"
                                          : "badge-warning rounded t-badge text-white"
                                      }
                                      onClick={() =>
                                        this.showIssue(data.projectId)
                                      }
                                    >
                                      {this.props.userIssue[0]}
                                    </span>
                                  </p>
                                </div>
                                <div className="box_body">
                                  <p className="course">{data.course}</p>
                                  <p className="generation">
                                    {data.generation}
                                  </p>
                                </div>
                                <div>
                                  <ProgressBar
                                    variant="success"
                                    now={this.props.userIssue[1]}
                                    label={`${this.props.userIssue[1]}%`}
                                  />
                                  <hr className="m-0 mt-2" />
                                  <div
                                    className="float-right  mt-2 "
                                    style={{ marginBottom: "-7px" }}
                                  >
                                    <Link
                                      className="text-decoration-none"
                                      to={`/task/view_task/${data.projectId}`}
                                    >
                                      More{" "}
                                      <i
                                        className="fa fa-chevron-right"
                                        aria-hidden="true"
                                      ></i>
                                    </Link>
                                  </div>
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
      </>
    );
  }
}
const mapStateToProps = (state) => {
  return {
    course: state.courseReducer.course,
    generation: state.generationReducer.generation,
    userProject: state.dashboardReducer.userProject,
    checkProject: state.dashboardReducer.check,
    userIssue: state.dashboardReducer.userIssue,
    projectAll: state.dashboardReducer.projectAll,
    projectIssue: state.dashboardReducer.projectIssue,
  };
};

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getCourse,
      getGeneration,
      getUserProject,
      getUserIssue,
      getAllProject,
      getAllIssues,
    },
    dispatch
  );
};
export default connect(mapStateToProps, mapDispatchToProps)(Dashboard);
