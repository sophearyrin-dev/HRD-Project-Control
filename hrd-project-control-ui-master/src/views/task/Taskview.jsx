import React, { PureComponent } from "react";
import {
  getTaskview,
  getIssseByMain,
} from "../../redux/actions/Task/taskAction";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import "./Task.css";
import Loading from "./../loading/loading";
import { Modal, ProgressBar, Table, Button, Alert } from "react-bootstrap";
import { Link } from "react-router-dom";
import { getSubTask } from "../../redux/actions/Task/taskAction";
import {
  updateTaskByUser,
  resolvedTask,
} from "../../redux/actions/Task/taskAction";
import { MDBBadge } from "mdbreact";
import Loader from "react-loader-spinner";
import localStorages from "../../redux/actions/Login/securityUtils/localStorages";
import Swal from "sweetalert2";
import { Redirect } from "react-router";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3000,
});
class Taskview extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      show_pId: this.props.match.params.projectId,
      isLoading: false,
      show: false,
      secondshow: false,
      showname: "",
      modalLoading: false,
      isLoadingModal: false,
      show2: false,
      user: localStorages(),
      shows: false,
      endate: "",
      subTask: "",
      mainTask: "",
      handler: "",
      date: new Date(),
      percentError: "",
      msg: "",
      alertMsg: "",
      issue: "",
      taskId: 0,
      percentage: 0,
      isUpdate: false,
      parentID: 0,
    };
  }

  componentDidMount() {
    this.props.getSubTask();
    this.props.getTaskview(this.state.show_pId);
  }

  handleChange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  showIssue = (id) => {
    this.setState({
      show2: true,
      isLoadingModal: true,
    });
    this.props.getIssseByMain(id).then(() =>
      this.setState({
        isLoadingModal: false,
      })
    );
  };

  handleShow = (e, tId) => {
    this.setState({ show: true, modalLoading: true, showname: e });
    this.props.getSubTask(tId, this.state.show_pId).then(() => {
      this.setState({
        parentID: tId,
        modalLoading: false,
      });
    });
  };

  handleResolved = (id) => {
    let tt = "";
    resolvedTask(id, tt, (res) => {
      Toast.fire({
        icon: "success",
        title: "You Have Resolved Your ask",
      });
    });

    setTimeout(() => {
      this.props.getSubTask(this.state.parentID, this.state.show_projecti);
    }, 2000);
  };

  handleEdit = (id, issue, percentage, end_date, handler, sub, main, msg) => {
    this.setState({
      show: false,
      shows: true,
      isUpdate: true,
      issue: issue,
      percentage: percentage,
      endate: end_date,
      subTask: sub,
      mainTask: main,
      handler: handler,
      taskId: id,
      msg: msg,
      date: new Date(),
    });
  };

  handleClose = () => {
    this.setState({ show: false, show2: false, shows: false });
  };

  handleCloses = () => {
    this.setState({ shows: false, show: true });
  };

  updateIssue = () => {
    this.setState({
      show: true,
    });
    const issue = {
      issue: this.state.issue,
      percentage: this.state.percentage,
      message: this.state.msg,
    };

    if (this.state.isUpdate) {
      updateTaskByUser(this.state.taskId, issue, (res) => {
        this.setState({
          alertMsg: res.data.message,
          isUpdate: false,
          updateId: "",
        });
        Toast.fire({
          icon: "success",
          title: this.state.alertMsg,
        });
      });
      setTimeout(() => {
        this.props.getSubTask(this.state.parentID, this.state.show_projecti);
      }, 1500);
    }
    this.setState({ shows: false });
  };

  getIcon = (color) => {
    if (color == 3) {
      return "danger";
    } else if (color == 2) {
      return "warning";
    } else if (color == 1) {
      return "info";
    } else if (color == 0) {
      return "secondary";
    }
  };

  getName = (name) => {
    if (name == 3) {
      return "Urgent";
    } else if (name == 2) {
      return "High";
    } else if (name == 1) {
      return "Medium";
    } else if (name == 0) {
      return "Low";
    }
  };

  render() {
    localStorage.setItem("cccDD", this.state.show_pId);
    return (
      <>
        <Modal
          show={this.state.shows}
          size="lg"
          backdrop="static"
          keyboard={false}
          onHide={this.handleCloses}
        >
          <Modal.Header closeButton>
            <Modal.Title>Add Issue</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <div className="row">
              <div className="col-12">
                <div className="form-group">
                  <label>Issue</label>
                  <input
                    onChange={this.handleChange}
                    value={this.state.issue}
                    type="text"
                    className="form-control"
                    name="issue"
                  />
                  <span className="text-warning">
                    * you can raise any issues here
                  </span>
                </div>
              </div>
            </div>
          </Modal.Body>
          <Modal.Footer>
            <Button
              variant="danger"
              className="btn-sm"
              onClick={this.handleCloses}
            >
              Close
            </Button>
            <Button
              className="btn-sm"
              variant="warning"
              onClick={() => this.updateIssue()}
            >
              Update
            </Button>
          </Modal.Footer>
        </Modal>

        <Modal
          show={this.state.show2}
          onHide={this.handleClose}
          size="md"
          backdrop="static"
          keyboard={false}
        >
          <Modal.Header closeButton>
            <Modal.Title>
              <h3>
                <i height={25} className="fas fa-exclamation-triangle">
                  {" "}
                </i>{" "}
                MAIN TASK ISSUES
              </h3>
            </Modal.Title>
          </Modal.Header>
          <Modal.Body className="height_model">
            <div className="text-center">
              {this.state.isLoadingModal && (
                <Loader type="Bars" color="#2f353a" height={30} width={30} />
              )}
            </div>

            {this.props.getIssueMain.length < 1 ? (
              <div
                className="text-center"
                hidden={this.state.isLoadingModal === true ? true : false}
              >
                <h3>THERE'S NO ISSUE FOR THIS MAIN TASK</h3>
              </div>
            ) : (
              <>
                {this.props.getIssueMain.map((data, i) => (
                  <Alert
                    key={i}
                    variant="warning"
                    hidden={this.state.isLoadingModal === true ? true : false}
                  >
                    {data.handler} :{" "}
                    <span className="text-danger">{data.issue}. </span>
                  </Alert>
                ))}
              </>
            )}
          </Modal.Body>
          <Modal.Footer>
            <Button variant="danger" size="sm" onClick={this.handleClose}>
              Close
            </Button>
          </Modal.Footer>
        </Modal>
        <Modal
          show={this.state.show}
          size="xl"
          dialogClassName="my-modal"
          backdrop="static"
          keyboard={false}
          onHide={this.handleClose}
        >
          <Modal.Header closeButton>
            <Modal.Title>{this.state.showname}</Modal.Title>
          </Modal.Header>
          <Modal.Body className="mdal-scroll">
            <div
              className="text-center"
              hidden={this.state.modalLoading ? false : true}
            >
              {this.state.modalLoading && (
                <Loader type="Bars" color="#2f353a" height={30} width={30} />
              )}
            </div>

            <Table
              bordered
              hover
              style={{ textAlign: "center" }}
              hidden={this.state.modalLoading ? true : false}
            >
              <thead>
                <tr>
                  <th>ID</th>
                  <th className=" sub_tbl">Subtask</th>
                  <th>Handler</th>
                  <th>StartDate</th>
                  <th>EndDate</th>
                  <th>Percentage</th>
                  <th>Priority</th>
                  <th>Issue</th>
                  <th>Message</th>
                  {this.state.user.role === "SUPER_MENTOR" && <th>Action</th>}
                </tr>
              </thead>
              <tbody>
                {this.props.subtask.map((datas, i) => (
                  <tr key={i}>
                    <td>{i + 1}</td>
                    <td className="text-truncate" title={datas.subTaskName}>
                      {datas.subTaskName}
                    </td>
                    <td>{datas.handler}</td>
                    <td>{datas.startDate}</td>
                    <td>{datas.endDate}</td>
                    <td>
                      <ProgressBar
                        style={{ width: "100%" }}
                        variant="success"
                        now={datas.percentage}
                        label={datas.percentage}
                      />
                    </td>
                    <td className="text-center">
                      <MDBBadge color={this.getIcon(datas.priority)}>
                        {" "}
                        {this.getName(datas.priority)}
                      </MDBBadge>
                    </td>
                    <td className=" text-truncate">{datas.issue}</td>
                    <td className=" text-truncate">{datas.message}</td>
                    {this.state.user.role === "SUPER_MENTOR" && (
                      <td className="">
                        {this.props.authenticated ? (
                          <Button
                            title="Resolve Issue"
                            type="button"
                            className="mx-1 btn btn-sm btn-info btn-md mb-1"
                            onClick={() => this.handleResolved(datas.subTaskId)}
                          >
                            Resolve
                          </Button>
                        ) : (
                          <Redirect to="/403"></Redirect>
                        )}
                        {this.props.authenticated ? (
                          <button
                            type="button"
                            className="mx-1 btn btn-sm btn-primary btn-md"
                            onClick={() =>
                              this.handleEdit(
                                datas.subTaskId,
                                datas.issue,
                                datas.percentage,
                                datas.endDate,
                                datas.handler,
                                datas.subTaskName,
                                datas.mainTaskName,
                                datas.message
                              )
                            }
                          >
                            <i className="fas fa-plus"></i>{" "}
                          </button>
                        ) : (
                          <Redirect to="/403"></Redirect>
                        )}
                      </td>
                    )}
                  </tr>
                ))}
              </tbody>
            </Table>
          </Modal.Body>
          <Modal.Footer>
            <Button size="sm" variant="danger" onClick={this.handleClose}>
              Close
            </Button>
          </Modal.Footer>
        </Modal>

        <h3>
          {" "}
          <i className="fas fa-tasks "></i> View Task Managment
        </h3>
        <div style={{ display: "flex", float: "right" }}>
          <div
            className="col-auto"
            style={{ marginTop: "30px", marginRight: "-15px" }}
          >
            <Link
              to={`/task/view_all_task/${this.state.show_pId}`}
              className="link"
            >
              <button type="filter" className="btn btn-primary shadow">
                All Task
              </button>
            </Link>
          </div>
          <div
            className="col-auto"
            style={{ marginTop: "30px", marginRight: "-12px" }}
          >
            <Link
              to={`/task/view_my_task/${this.state.show_pId}`}
              className="link"
            >
              <button type="filter" className="btn btn-primary shadow">
                My Task
              </button>
            </Link>
          </div>
        </div>
        <div className="clearfix"> </div>
        <br />

        <div>
          {this.props.taskview.length < 1 ? (
            <Loading height="60vh" />
          ) : (
            <div className="row">
              {this.props.taskview.map((datas, i) => (
                <div
                  key={i}
                  className="col-lg-3 col-md-6 col-sm-12  mt-lg-0  mt-2"
                >
                  <div
                    className="card text-white w-100 shadow"
                    style={{ borderRadius: "9px" }}
                  >
                    <div
                      className="card-header"
                      style={{ borderRadius: "9px" }}
                    >
                      <p
                        className="text-truncate title-task"
                        title={datas.parentTaskName}
                      >
                        {datas.parentTaskName}
                      </p>
                    </div>
                    <div
                      className="card-body"
                      style={{ padding: "1.25rem 1.25rem 0rem 1.25rem" }}
                    >
                      <div className="row">
                        <p className="col-5" style={{ color: "black" }}>
                          Progress :
                        </p>
                        <div className="col-7">
                          <ProgressBar
                            style={{ width: "110%", marginLeft: "-1pc" }}
                            variant="success"
                            now={datas.percentage}
                            label={datas.percentage}
                          />
                        </div>
                      </div>

                      <div className="row" style={{ marginTop: "-9px" }}>
                        <p className="col-5" style={{ color: "black" }}>
                          Start-Date{" "}
                          <span style={{ marginLeft: ".1rem" }}>:</span>
                        </p>
                        <p style={{ color: "red" }}>{datas.startDate}</p>
                      </div>
                      <div className="row" style={{ marginTop: "-9px" }}>
                        <p className="col-5" style={{ color: "black" }}>
                          End-Date{" "}
                          <span style={{ marginLeft: ".5rem" }}> : </span>
                        </p>
                        <p style={{ color: "red" }}>{datas.endDate}</p>
                      </div>
                      <div className="row" style={{ marginTop: "-9px" }}>
                        <p
                          className="col-5 text-warning"
                          style={{ color: "black" }}
                        >
                          Issue <span style={{ marginLeft: ".5rem" }}> : </span>
                          <span className="badge-warning t-badge rounded text-white">
                            {datas.numberOfIssue}
                          </span>
                        </p>
                      </div>
                    </div>
                    <div className="col-12 ">
                      <div className="float-right mb-2">
                        {this.state.user.role !== "MEMBER" ? (
                          <>
                            <Link
                              to={{
                                pathname: `/task/add_task/${datas.parentTaskId}`,
                                state: { proID: this.state.show_pId },
                              }}
                            >
                              <i className="fas fa-plus mx-1 text-dark"></i>
                            </Link>

                            <Link
                              to={{
                                pathname: `/task/edit_task/${datas.parentTaskId}`,
                                state: { proID: this.state.show_pId },
                              }}
                            >
                              <i className="fas fa-edit   mx-1  text-dark"></i>
                            </Link>
                          </>
                        ) : (
                          <span></span>
                        )}

                        <i
                          className="fas fa-eye    mx-1 text-dark pointer-eye"
                          onClick={this.handleShow.bind(
                            this,
                            datas.parentTaskName,
                            datas.parentTaskId
                          )}
                        ></i>

                        <i
                          className={
                            datas.numberOfIssue < 1
                              ? "fas fa-exclamation-triangle  mx-1 text-warning test pointer-eye"
                              : " fas fa-exclamation-triangle  mx-1 text-warning pointer-eye"
                          }
                          onClick={() => {
                            this.showIssue(datas.parentTaskId);
                          }}
                        ></i>
                      </div>
                    </div>
                  </div>
                </div>
              ))}
              <br />
            </div>
          )}
        </div>
      </>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    taskview: state.taskReducer.taskview,
    subtask: state.taskReducer.subtask,
    getIssueMain: state.taskReducer.getIssueMain,
    authenticated: state.authenticatedReducer.authenticated,
  };
};

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getSubTask,
      getTaskview,
      getIssseByMain,
    },
    dispatch
  );
};
export default connect(mapStateToProps, mapDispatchToProps)(Taskview);
