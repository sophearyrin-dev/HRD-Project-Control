import React, { Component } from "react";
import { Modal, Button } from "react-bootstrap";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";
import Loading from "./../loading/loading";
import Swal from "sweetalert2";
import { getProjectOne } from "../../redux/actions/ProjectDivision/Project/ProjectAction";
import localStorages from "../../redux/actions/Login/securityUtils/localStorages";
import { Redirect } from "react-router-dom";
import {
  assignMainTask,
  getUserByPro,
  assingSubtask,
} from "../../redux/actions/Task/taskAction";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3000,
});

class TaskAssign extends Component {
  constructor(props) {
    super(props);
    this.state = {
      checked: false,
      show: true,
      topic: "",
      startDate: "",
      endDate: "",
      handler: 0,
      priority: "",
      percentage: 0,
      showData: [],
      name: [],
      assignGl: 0,
      typeName: "",
      results: "",
      alertMsg: "",
      topicError: "",
      startError: "",
      endError: "",
      handlerError: "",
      priorityError: "",
      percentageError: "",

      isLoading: false,
      user: localStorages(),
      taskId: this.props.match.params.id,
    };
  }

  componentDidMount() {
    this.props.getProjectOne(this.props.location.state.proID);
    this.state.user.role !== "MEMBER" &&
      this.props.getUserByPro(this.props.location.state.proID);
  }

  handleClose = () => {
    this.setState({ show: false });
  };

  handleShow = () => {
    this.setState({ show: true });
  };

  handleChange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  addScheduleType = () => {
    this.setState({
      show: false,
    });
  };

  validate = () => {
    let topicError = "";
    let startError = "";
    let endError = "";
    let handlerError = "";
    let priorityError = "";
    let percentError = "";

    if (!this.state.topic) {
      topicError = "*Topic Type Cannot Be Blank";
    }
    if (!this.state.startDate) {
      startError = "*Start Date Type Cannot Be Blank";
    }
    if (!this.state.endDate) {
      endError = "*End Date Type Cannot Be Blank";
    }
    if (!this.state.handler) {
      handlerError = "* Handler Cannot Be Blank";
    }
    if (!this.state.priority) {
      priorityError = "*Priority Type Cannot Be Blank";
    }
    if (!this.state.percentage) {
      percentError = "*Percentage Cannot Be Blank";
    } else if (this.state.percentage > 100 || this.state.percentage < 0) {
      percentError = "* Percentage Cannot Not Be More than 100 or Less Than 0";
    }
    if (
      topicError ||
      startError ||
      endError ||
      handlerError ||
      priorityError ||
      percentError
    ) {
      this.setState({
        topicError,
        startError,
        endError,
        handlerError,
        priorityError,
        percentError,
      });
      return false;
    }
    return true;
  };
  // id, maintask,
  handleAssignMain = () => {
    let id = this.state.taskId;
    let maintask = {
      handlerId: this.state.assignGl,
    };
    assignMainTask(id, maintask, (res) => {
      this.setState({
        show: false,
        alertMsg: res.data.message,
        topic: "",
      });
      Toast.fire({
        icon: "success",
        title: this.state.alertMsg,
      });
    });
  };

  handleAdd = () => {
    let topic = {
      subTaskName: this.state.topic,
      startDate: this.state.startDate,
      endDate: this.state.endDate,
      handlerId: this.state.handler,
      percentage: this.state.percentage,
      priority: this.state.priority,
    };

    if (this.validate()) {
      let results = this.state.showData;
      results.push(topic);
      this.setState({
        showData: results,
      });
    }
    console.log(this.state.showData);
  };

  getTypeName = (name) => {
    this.setState({
      typeName: name,
    });
  };

  handleSubmit = () => {
    this.setState({
      isLoading: true,
    });
    let subTask = {
      parentTaskId: this.state.taskId,
      projectId: this.props.location.state.proID,
      subTaskApiRequests: this.state.showData,
    };

    assingSubtask(subTask, (res) => {
      this.setState({
        alertMsg: res.data.message,
        topic: "",
      });
      Toast.fire({
        icon: "success",
        title: this.state.alertMsg,
      });
      this.props.history.push(
        `/task/view_task/${this.props.location.state.proID}`
      );
    });
  };

  deleteData = (i) => {
    this.state.showData.splice(i, 1);
    this.setState({ showData: this.state.showData });
  };

  render() {
    return this.state.isLoading ? (
      <Loading height="75vh" />
    ) : this.state.user.role !== "MEMBER" ? (
      <div>
        <h3>
          <i class="fas fa-tasks    "></i> Sub Task Assign
        </h3>
        <h3>
          <i class="fab fa-product-hunt"></i>{" "}
          <u>{this.props.projectOne.name}</u>
        </h3>
        {this.state.isLoading ? (
          <Loading height="75vh" />
        ) : (
          <>
            <Modal
              show={this.state.show}
              size="md"
              backdrop="static"
              keyboard={false}
              onHide={this.handleClose}
            >
              <Modal.Header closeButton>
                <Modal.Title>Assign Main Task</Modal.Title>
              </Modal.Header>
              <Modal.Body>
                <div className="form-group">
                  <label>Member</label>
                  <select
                    className="custom-select "
                    value={this.state.assignGl}
                    onChange={this.handleChange}
                    name="assignGl"
                  >
                    <option defaultValue="">Choose...</option>
                    {this.props.getUserPro.map((datas, i) => (
                      <option key={i} value={datas.user.userId}>
                        {datas.user.username}
                      </option>
                    ))}
                  </select>
                </div>
              </Modal.Body>
              <Modal.Footer>
                <Button
                  variant="info"
                  className="btn-sm"
                  onClick={this.handleClose}
                >
                  Close
                </Button>
                <Button
                  className="btn-sm"
                  variant="primary"
                  onClick={() => {
                    this.handleAssignMain();
                  }}
                >
                  Submit
                </Button>
              </Modal.Footer>
            </Modal>

            <div className="row">
              <div className="col-4">
                <div className="form-group">
                  <label>Sub Task</label>
                  <input
                    onChange={this.handleChange}
                    value={this.state.topic}
                    type="text"
                    className="form-control"
                    placeholder="Enter Sub Task"
                    name="topic"
                    autoComplete="off"
                    autoFocus
                  />
                  <div className="text-danger ">{this.state.topicError}</div>
                </div>
              </div>
              <div className="col-4">
                <div className="form-group">
                  <label>Start Date</label>
                  <input
                    onChange={this.handleChange}
                    value={this.state.startDate}
                    type="date"
                    className="form-control"
                    name="startDate"
                    autoComplete="off"
                  />
                  <div className="text-danger">{this.state.startError}</div>
                </div>
              </div>
              <div className="col-4">
                <div className="form-group">
                  <label>End Date</label>
                  <input
                    onChange={this.handleChange}
                    value={this.state.endDate}
                    type="date"
                    className="form-control"
                    name="endDate"
                    autoComplete="off"
                  />
                  <div className="text-danger ">{this.state.endError}</div>
                </div>
              </div>
              <div className="col-4">
                <div className="form-group">
                  <label>Handler</label>
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
                  <div className="text-danger ">{this.state.handlerError}</div>
                </div>
              </div>

              <div className="col-4">
                <div className="form-group">
                  <label>Priority</label>
                  <select
                    className="custom-select "
                    value={this.state.priority}
                    onChange={this.handleChange}
                    name="priority"
                  >
                    <option defaultValue="">Choose...</option>
                    <option value="3">Urgent</option>
                    <option value="2">High</option>
                    <option value="1">Medium</option>
                    <option value="0">Low</option>
                  </select>
                  <div className="text-danger ">{this.state.priorityError}</div>
                </div>
              </div>
              <div className="col-4">
                <div className="form-group">
                  <label>Percentage</label>
                  <input
                    onChange={this.handleChange}
                    value={this.state.percentage}
                    type="number"
                    className="form-control"
                    name="percentage"
                    autoComplete="off"
                  />
                  <div className="text-danger ">{this.state.percentError}</div>
                </div>
              </div>

              <div className="col-12">
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
                <Button
                  className=" float-right mr-2"
                  variant="info"
                  onClick={() => {
                    this.handleAdd();
                  }}
                >
                  Add
                </Button>{" "}
              </div>
            </div>

            <h3>Data: </h3>
            <table className="table table-striped table-bordered">
              <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Sub Task</th>
                  <th scope="col">Start Date</th>
                  <th scope="col">End Date</th>
                  <th scope="col">Handler</th>
                  <th scope="col">Priority</th>
                  <th scope="col">Percentage</th>
                  <th scope="5%">Action</th>
                </tr>
              </thead>
              <tbody>
                {this.state.showData.map((datas, i) => (
                  <tr key={i}>
                    <td>{i + 1}</td>
                    <td>{datas.subTaskName}</td>
                    <td>{datas.startDate}</td>
                    <td>{datas.endDate}</td>
                    <td>{datas.handlerId}</td>
                    <td>{datas.priority}</td>
                    <td>{datas.percentage}</td>
                    <td className="text-center">
                      <Button
                        className="btn-sm"
                        variant="danger"
                        onClick={(e) => {
                          this.deleteData(i);
                        }}
                      >
                        <i className="fas fa-times"></i>
                      </Button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </>
        )}
      </div>
    ) : (
      <Redirect to="/403"></Redirect>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    projectType: state.projectTypeReducer.projectType,
    authenticated: state.authenticatedReducer.authenticated,
    taskID: state.taskReducer.taskview,
    getUserPro: state.taskReducer.getUserPro,
    projectOne: state.projectReducer.projectOne,
  };
};
const mapDispatchToProps = (dispatch) => {
  return bindActionCreators({ getUserByPro, getProjectOne }, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(TaskAssign);
