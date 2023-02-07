import React, { Component } from "react";
import "./Project.css";
import {
  getProjectScheduleOne,
  getProjectSchedule,
  deleteProjectScheduleOne,
  updateProjectScheduleOne,
} from "../../../redux/actions/Schedule/projectSchedule/projectScheduleAction";
import { Modal, Button } from "react-bootstrap";
import Swal from "sweetalert2";
import { getProjectType } from "../../../redux/actions/Schedule/scheduleType/scheduleTypeActions";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";
import localStorage from "../../../redux/actions/Login/securityUtils/localStorages";
import Loading from "./../../loading/loading";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3000,
});

class viewProjectSchedule extends Component {
  state = {
    show: false,
    showGroupMeeting: false,
    topic: "",
    startDate: "",
    endDate: "",
    isUpdate: false,
    updateId: "",
    alertMsg: "",
    isLoading: "true",
    percentage: 0,
    compare: [],
    user: localStorage(),
    topicError: "",
    startError: "",
    endError: "",
    percentError: "",
  };

  handleEdit = (id, topic, startDate, endDate, percentage) => {
    this.setState({
      show: true,
      isUpdate: true,
      topic: topic,
      startDate: startDate,
      endDate: endDate,
      percentage: percentage,
      updateId: id,
    });
    console.log(this.state);
  };

  handleChange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  handleClose = () => {
    this.setState({
      show: false,
      topicError: "",
      startError: "",
      endError: "",
      percentError: "",
    });
  };

  componentDidMount() {
    const {
      match: { params },
    } = this.props;
    this.props.getProjectScheduleOne(params.id).then(() => {
      this.setState({
        isLoading: false,
      });
    });
  }

  validate = () => {
    let topicError = "";
    let startError = "";
    let endError = "";
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
    if (!this.state.percentage) {
      percentError = "*Percentage Cannot Be Blank";
    } else if (this.state.percentage > 100 || this.state.percentage < 0) {
      percentError = "* Percentage Cannot Not Be More than 100 or Less Than 0";
    }
    if (topicError || startError || endError || percentError) {
      this.setState({
        topicError,
        startError,
        endError,
        percentError,
      });
      return false;
    }
    return true;
  };

  updateProjectTopic = () => {
    const schedule = {
      typeId: this.state.schedule,
      endDate: this.state.endDate,
      name: this.state.topic,
      projectScheduleId: this.state.updateId,
      startDate: this.state.startDate,
      percentage: this.state.percentage,
    };
    if (this.validate()) {
      if (this.state.isUpdate) {
        updateProjectScheduleOne(this.state.updateId, schedule, (res) => {
          this.setState({
            alertMsg: res.data.message,
            isUpdate: false,
            updateId: "",
            topicError: "",
            startError: "",
            endError: "",
            percentError: "",
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
          this.props.getProjectScheduleOne(params.id);
        }, 1200);
      }
      this.setState({ show: false });
    }
  };

  handleDelete = (id) => {
    this.props.deleteProjectScheduleOne(id);
    setTimeout(() => {
      const {
        match: { params },
      } = this.props;
      this.props.getProjectScheduleOne(params.id);
    }, 1500);
  };

  render() {
    return (
      <div>
        <Modal
          show={this.state.show}
          size="lg"
          backdrop="static"
          keyboard={false}
          onHide={this.handleClose}
        >
          <Modal.Header closeButton>
            <Modal.Title>Update Project Topic</Modal.Title>
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
            <Button
              className="btn-sm"
              variant="warning"
              onClick={() => {
                this.updateProjectTopic();
              }}
            >
              Update
            </Button>
          </Modal.Footer>
        </Modal>

        <h3>
          {" "}
          <i className="fas fa-calendar-alt    "></i> View Project Schedule
        </h3>
        <br />
        {this.state.isLoading ? (
          <Loading height="70vh" />
        ) : (
          <table className="table table-striped table-bordered">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Topic</th>
                <th scope="col">Start Date</th>
                <th scope="col">End Date</th>
                <th scope="col">Percentage</th>
                {this.state.user.role === "SUPER_MENTOR" ? (
                  <th scope="col" className="text-center" width="10%">
                    {" "}
                    Action{" "}
                  </th>
                ) : (
                  <span></span>
                )}
              </tr>
            </thead>
            <tbody>
              {this.props.projectScheduleOne &&
              this.props.projectScheduleOne.length <= 0 ? (
                <tr align="center">
                  <td colSpan="6">
                    There is no data for this project Schedule{" "}
                  </td>
                </tr>
              ) : (
                this.props.projectScheduleOne.map((datas, i) => (
                  <tr key={i}>
                    <th scope="row">{i + 1}</th>
                    <td>{datas.name}</td>
                    <td>{datas.startDate}</td>
                    <td>{datas.endDate}</td>
                    <td>{datas.percentage}</td>
                    {this.state.user.role === "SUPER_MENTOR" ? (
                      <td className="text-center">
                        <button
                          type="button"
                          className="mx-1 btn btn-sm btn-primary btn-md"
                          onClick={() =>
                            this.handleEdit(
                              datas.id,
                              datas.name,
                              datas.startDate,
                              datas.endDate,
                              datas.percentage
                            )
                          }
                        >
                          <i className="fas fa-edit "></i>{" "}
                        </button>
                        <button
                          onClick={() => {
                            this.handleDelete(datas.id);
                          }}
                          type="button"
                          className="mx-1 btn btn-sm btn-danger btn-md"
                        >
                          {" "}
                          <i className="fas fa-trash-alt    "></i>
                        </button>
                      </td>
                    ) : (
                      <span></span>
                    )}
                  </tr>
                ))
              )}
            </tbody>
            <tfoot>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Topic</th>
                <th scope="col">Start Date</th>
                <th scope="col">End Date</th>
                <th scope="col">Percentage</th>

                {this.state.user.role === "SUPER_MENTOR" ? (
                  <th className="text-center" scope="col">
                    Action
                  </th>
                ) : (
                  <span></span>
                )}
              </tr>
            </tfoot>
          </table>
        )}
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    projectScheduleOne: state.projectScheduleReducer.projectScheduleOne,
    projectType: state.projectTypeReducer.projectType,
    projectSchedule: state.projectScheduleReducer.projectSchedule,
  };
};

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getProjectScheduleOne,
      getProjectType,
      getProjectSchedule,
      deleteProjectScheduleOne,
    },
    dispatch
  );
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(viewProjectSchedule);
