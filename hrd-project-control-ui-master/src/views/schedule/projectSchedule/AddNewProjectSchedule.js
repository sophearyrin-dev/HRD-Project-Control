import React, { Component } from "react";
import "./Project.css";
import { Modal, Button } from "react-bootstrap";
import { getProjectType } from "../../../redux/actions/Schedule/scheduleType/scheduleTypeActions";
import { bindActionCreators } from "redux";
import { addProjectTopic } from "../../../redux/actions/Schedule/projectSchedule/projectScheduleAction";
import { connect } from "react-redux";
import DayPicker, { DateUtils } from "react-day-picker";
import "react-day-picker/lib/style.css";
import Loading from "./../../loading/loading";
import Swal from "sweetalert2";

import localStorage from "../../../redux/actions/Login/securityUtils/localStorages";
import { Redirect } from "react-router-dom";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3000,
});
class AddNewProjectSchedule extends Component {
  constructor(props) {
    super(props);
    this.state = {
      show: true,
      topic: "",
      startDate: "",
      endDate: "",
      showData: [],
      percentage: 0,
      name: [],
      type: 0,
      typeName: "",
      results: "",
      alertMsg: "",
      selectedDays: [],
      topicError: "",
      startError: "",
      endError: "",
      percentError: "",
      isLoading: false,
      user: localStorage(),
      typeError: "",
    };
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

  validateModal = () => {
    let typeError = "";

    if (!this.state.type) {
      typeError = "* Type Cannot Be Blank";
    }
    if (typeError) {
      this.setState({
        typeError,
      });
      return false;
    }
    return true;
  };

  componentDidMount() {
    this.state.user.role === "SUPER_MENTOR" && this.props.getProjectType();
  }

  handleClose = () => {
    if (this.validateModal()) {
      this.setState({ show: false });
    }
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

  handleAdd = () => {
    let topic = {
      name: this.state.topic,
      startDate: this.state.startDate,
      endDate: this.state.endDate,
      percentage: this.state.percentage,
    };

    if (this.validate()) {
      let results = this.state.showData;
      results.push(topic);
      this.setState({
        showData: results,
        topic: "",
        topicError: "",
        startError: "",
        endError: "",
        percentError: "",
      });
    }
  };

  // getTypeName = (name) => {
  //   this.setState({
  //     typeName: name,
  //   });
  // };

  handleSubmit = () => {
    this.setState({
      isLoading: true,
    });
    let tp = {
      projectScheduleApiRequst: {
        groupMeeting: this.state.selectedDays.toString(),
        typeId: this.state.type,
      },
      projectTopicApiRequestList: this.state.showData,
    };

    addProjectTopic(tp, (res) => {
      this.setState({
        alertMsg: res.data.message,
        topic: "",
      });
      Toast.fire({
        icon: "success",
        title: this.state.alertMsg,
      });
      this.props.history.push("/schedule/project_schedule");
    });
  };

  deleteData = (i) => {
    this.state.showData.splice(i, 1);
    this.setState({ showData: this.state.showData });
  };

  handleDayClick = (day, { selected }) => {
    const { selectedDays } = this.state;
    if (selected) {
      const selectedIndex = selectedDays.findIndex((selectedDay) =>
        DateUtils.isSameDay(selectedDay, day)
      );
      selectedDays.splice(selectedIndex, 1);
    } else {
      selectedDays.push(day);
    }
    this.setState({ selectedDays });
  };

  render() {
    return this.state.isLoading ? (
      <Loading height="75vh" />
    ) : this.state.user.role === "SUPER_MENTOR" ? (
      <div>
        <h3>
          {" "}
          <i className="fas fa-calendar-alt    "></i> Add New Projet Schedule
        </h3>

        <Modal
          show={this.state.show}
          size="md"
          backdrop="static"
          keyboard={false}
          onHide={this.handleClose}
        >
          <Modal.Header closeButton>
            <Modal.Title>Select Schedule Type/Group Meeting</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <div className="form-group">
              <label>Schedule Type</label>
              <select
                className="custom-select "
                value={this.state.type}
                onChange={this.handleChange}
                name="type"
              >
                <option defaultValue="">Choose...</option>
                {this.props.projectType.map((datas, i) => (
                  <option key={i} value={datas.typeId}>
                    {datas.name}-{datas.generation.name}-{datas.course.name}
                  </option>
                ))}
              </select>
              <div className="text-danger ">{this.state.typeError}</div>

              <div className="text-center">
                <DayPicker
                  selectedDays={this.state.selectedDays}
                  onDayClick={this.handleDayClick}
                />
              </div>
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
              variant="warning"
              onClick={() => {
                this.handleClose();
              }}
            >
              Save Changes
            </Button>
          </Modal.Footer>
        </Modal>

        <div className="row">
          <div className="col-6">
            <div className="form-group">
              <label>Topic</label>
              <input
                onChange={this.handleChange}
                value={this.state.topic}
                type="text"
                className="form-control"
                placeholder="Enter Document Topic"
                name="topic"
                autoComplete="off"
                autoFocus
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
                autoComplete="off"
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
                autoComplete="off"
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
                name="percentage"
                autoComplete="off"
              />
              <div className="text-danger ">{this.state.percentError}</div>
            </div>
          </div>
          <div className="col-12">
            <Button
              className=" float-right mr-2"
              variant="info"
              onClick={() => {
                this.handleAdd();
              }}
            >
              Add
            </Button>{" "}
            <Button
              className=" float-right mr-2"
              variant="danger"
              onClick={() => {
                this.handleShow();
              }}
            >
              Change Schedule Type
            </Button>
          </div>
          <div className="col-12 mt-1">
            {this.props.authenticated ? (
              <button
              title="Please add topic before submit"
                type="submit"
                onClick={this.handleSubmit}
                className="btn btn-primary float-right mr-2"
              >
                Submit
              </button>
            ) : (
              <Redirect to="/403"></Redirect>
            )}
          </div>
        </div>
        {/* <h3 className="mt-3">Project Schedule Type: {this.state.typeName} </h3> */}

        <h3>Data: </h3>
        <table className="table table-striped table-bordered">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Topic</th>
              <th scope="col">Start Date</th>
              <th scope="col">End Date</th>
              <th scope="col">Percentage</th>
              <th width="5%" className="text-center">
                Action
              </th>
            </tr>
          </thead>
          <tbody>
            {this.state.showData.map((datas, i) => (
              <tr key={i}>
                <td>{i + 1}</td>
                <td>{datas.name}</td>
                <td>{datas.startDate}</td>
                <td>{datas.endDate}</td>
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
  };
};
const mapDispatchToProps = (dispatch) => {
  return bindActionCreators({ addProjectTopic, getProjectType }, dispatch);
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AddNewProjectSchedule);
