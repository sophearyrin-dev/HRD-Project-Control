import React, { Component } from "react";
import {
  getSubByMain,
  deleteSubTask,
  updateTaskBymentor,
  getUserByPro,
} from "../../redux/actions/Task/taskAction";
import { getUser } from "../../redux/actions//UserControl/usercontrolAction";
import { Modal, Button } from "react-bootstrap";
import Swal from "sweetalert2";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";
import localStorages from "../../redux/actions/Login/securityUtils/localStorages";
import Loading from "./../loading/loading";
import { MDBBadge } from "mdbreact";
import { Redirect } from "react-router-dom";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3000,
});

let cc_id = localStorage.getItem("cccDD");

class EditTask extends Component {
  constructor(props) {
    super(props);
    this.state = {
      show: false,
      showGroupMeeting: false,
      topic: "",
      startDate: "",
      endDate: "",
      priority: "",
      handler: 0,
      isUpdate: false,
      updateId: "",
      alertMsg: "",
      isLoading: "true",
      percentage: 0,
      compare: [],
      user: localStorages(),
      topicError: "",
      startError: "",
      endError: "",
      percentError: "",
      priorityError: "",
      handlerError: "",
    };
  }

  handleEdit = (
    id,
    topic,
    startDate,
    endDate,
    percentage,
    priority,
    handler
  ) => {
    this.setState({
      show: true,
      isUpdate: true,
      topic: topic,
      startDate: startDate,
      endDate: endDate,
      percentage: percentage,
      priority: priority,
      updateId: id,
      handler: handler,
    });
  };

  handleChange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  handleClose = () => {
    this.setState({ show: false });
  };

  componentDidMount() {
    const {
      match: { params },
    } = this.props;
    this.props.getUserByPro(cc_id);

    this.props.getUser();
    this.props.getSubByMain(params.id).then(() => {
      this.setState({
        isLoading: false,
      });
    });
  }

  validate = () => {
    let topicError = "";
    let startError = "";
    let endError = "";
    let handlerError = "";
    let percentError = "";
    let priorityError = "";
    if (!this.state.topic) {
      topicError = "*Topic Cannot Be Blank";
    }
    if (!this.state.startDate) {
      startError = "*Start Date Cannot Be Blank";
    }
    if (!this.state.handler) {
      handlerError = "* Please Select a Handler";
    }
    if (!this.state.endDate) {
      endError = "*End Date Cannot Be Blank";
    }
    if (!this.state.priority) {
      priorityError = "* Priority Cannot Be Blank";
    }
    if (!this.state.percentage) {
      percentError = "* Percentage Cannot Be Blank";
    } else if (this.state.percentage > 100 || this.state.percentage < 0) {
      percentError = "* Percentage Cannot Not Be More than 100 or Less Than 0";
    }
    if (
      topicError ||
      startError ||
      endError ||
      percentError ||
      priorityError ||
      handlerError
    ) {
      this.setState({
        topicError,
        startError,
        endError,
        percentError,
        priorityError,
        handlerError,
      });
      return false;
    }
    return true;
  };

  updateSubTask = () => {
    const subTaskMen = {
      priority: this.state.priority,
      endDate: this.state.endDate,
      name: this.state.topic,
      handlerId: this.state.handler,
      startDate: this.state.startDate,
      percentage: this.state.percentage,
    };
    if (this.validate()) {
      if (this.state.isUpdate) {
        updateTaskBymentor(this.state.updateId, subTaskMen, (res) => {
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
          const {
            match: { params },
          } = this.props;
          this.props.getSubByMain(params.id);
        }, 1200);
      }
      this.setState({ show: false });
    }
  };

  handleDelete = (id) => {
    this.props.deleteSubTask(id);
    setTimeout(() => {
      const {
        match: { params },
      } = this.props;
      this.props.getSubByMain(params.id);
    }, 1700);
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
    return this.state.user.role !== "MEMBER" ? (
      <div>
        <Modal
          show={this.state.show}
          size="lg"
          backdrop="static"
          keyboard={false}
          onHide={this.handleClose}
        >
          <Modal.Header closeButton>
            <Modal.Title>Update Sub Task</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <div className="row">
              <div className="col-6">
                <div className="form-group">
                  <label>Topic</label>
                  <input
                    onChange={this.handleChange}
                    value={this.state.topic}
                    type="text"
                    className="form-control"
                    placeholder="Enter Schedule Topic"
                    name="topic"
                  />
                  <div className="text-danger ">{this.state.topicError}</div>
                </div>
              </div>
              <div className="col-6">
                <div className="form-group">
                  <label>Member</label>
                  <select
                    className="custom-select "
                    value={this.state.handler}
                    onChange={this.handleChange}
                    name="handler"
                  >
                    <option defaultValue="">Choose...</option>
                    {this.props.getUserPro.map((datas, i) => (
                      <option key={i} value={datas.user.userId}>
                        {datas.user.username}
                      </option>
                    ))}
                  </select>
                </div>
              </div>
              <div className="col-6">
                <div className="form-group">
                  <label>Start Date</label>
                  <input
                    onChange={this.handleChange}
                    value={this.state.startDate}
                    type="date"
                    className="form-control"
                    name="startDate"
                  />
                  <div className="text-danger ">{this.state.startError}</div>
                </div>
              </div>
              <div className="col-6">
                <div className="form-group">
                  <label>End Date</label>
                  <input
                    onChange={this.handleChange}
                    value={this.state.endDate}
                    type="date"
                    className="form-control"
                    name="endDate"
                  />
                  <div className="text-danger ">{this.state.endError}</div>
                </div>
              </div>
              <div className="col-6">
                <div className="form-group">
                  <label>Percentage</label>
                  <input
                    onChange={this.handleChange}
                    value={this.state.percentage}
                    type="number"
                    max="100"
                    className="form-control"
                    placeholder="Enter Topic Percentage"
                    name="percentage"
                  />
                  <div className="text-danger ">{this.state.percentError}</div>
                </div>
              </div>
              <div className="col-6">
                <div className="form-group">
                  <label>Priority</label>
                  <select
                    className="custom-select "
                    value={this.state.priority}
                    onChange={this.handleChange}
                    name="priority"
                  >
                    <option defaultValue={!this.state.priority}>
                      Choose...
                    </option>
                    <option value="3">Urgent</option>
                    <option value="2">High</option>
                    <option value="1">Medium</option>
                    <option value="0">Low</option>
                  </select>
                  <div className="text-danger ">{this.state.priorityError}</div>
                </div>
              </div>
            </div>
          </Modal.Body>
          <Modal.Footer>
            <Button
              variant="danger"
              className="btn-sm"
              onClick={this.handleClose}
            >
              Close
            </Button>
            {this.props.authenticated ? (
              <Button
                className="btn-sm"
                variant="warning"
                onClick={() => {
                  this.updateSubTask();
                }}
              >
                Update
              </Button>
            ) : (
              <Redirect to="/403"></Redirect>
            )}
          </Modal.Footer>
        </Modal>

        <h3>
          {" "}
          <i className="fas fa-tasks   "></i> Edit Task
        </h3>
        <br />
        {this.state.isLoading ? (
          <Loading height="70vh" />
        ) : (
          <table className="table table-striped table-bordered">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Sub Task</th>
                <th scope="col">Start Date</th>
                <th scope="col">End Date</th>
                <th scope="col">Handler</th>
                <th scope="col" className="text-center">
                  Priority
                </th>
                <th scope="col">Percentage</th>
                <th scope="col" className="text-center" width="10%">
                  {" "}
                  Action{" "}
                </th>
              </tr>
            </thead>
            <tbody>
              {this.props.getSubByID.length < 1 ? (
                <tr align="center">
                  <td colSpan="8">There is no data for this task </td>
                </tr>
              ) : (
                this.props.getSubByID.map((datas, i) => (
                  <tr key={i}>
                    <th scope="row">{i + 1}</th>
                    <td>{datas.subTaskName}</td>
                    <td>{datas.startDate}</td>
                    <td>{datas.endDate}</td>
                    <td>{datas.handler}</td>
                    <td className="text-center">
                      <MDBBadge color={this.getIcon(datas.priority)}>
                        {" "}
                        {this.getName(datas.priority)}
                      </MDBBadge>
                    </td>
                    <td>{datas.percentage}</td>
                    {this.props.authenticated ? (
                      <td className="text-center">
                        <button
                          type="button"
                          className="mx-1 btn btn-sm btn-primary btn-md"
                          onClick={() =>
                            this.handleEdit(
                              datas.subTaskId,
                              datas.subTaskName,
                              datas.startDate,
                              datas.endDate,
                              datas.percentage,
                              datas.priority,
                              datas.handlerId
                            )
                          }
                        >
                          <i className="fas fa-edit "></i>{" "}
                        </button>
                        <button
                          onClick={() => {
                            this.handleDelete(datas.subTaskId);
                          }}
                          type="button"
                          className="mx-1 btn btn-sm btn-danger btn-md"
                        >
                          {" "}
                          <i className="fas fa-trash-alt    "></i>
                        </button>
                      </td>
                    ) : (
                      <Redirect to="/403"></Redirect>
                    )}
                  </tr>
                ))
              )}
            </tbody>
            <tfoot>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Sub Task</th>
                <th scope="col">Start Date</th>
                <th scope="col">End Date</th>
                <th scope="col">Handler</th>
                <th scope="col" className="text-center">
                  Priority
                </th>
                <th scope="col">Percentage</th>
                <th className="text-center" scope="col">
                  Action
                </th>
              </tr>
            </tfoot>
          </table>
        )}
      </div>
    ) : (
      <Redirect to="/403"></Redirect>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    getSubByID: state.taskReducer.getSubByID,
    getUserPro: state.taskReducer.getUserPro,
    user: state.userReducer.user,
    authenticated: state.authenticatedReducer.authenticated,
  };
};

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getSubByMain,
      deleteSubTask,
      getUserByPro,
      getUser,
    },
    dispatch
  );
};

export default connect(mapStateToProps, mapDispatchToProps)(EditTask);
