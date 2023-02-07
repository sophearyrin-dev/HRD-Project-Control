import React, { Component } from "react";
import "./Document.css";
import { Modal, Button } from "react-bootstrap";
import { getProjectType } from "../../../redux/actions/Schedule/scheduleType/scheduleTypeActions";
import { getGeneration } from "../../../redux/actions/Generation/generationAction";
import { bindActionCreators } from "redux";
import { addDocumenetTopic } from "../../../redux/actions/Schedule/documentSchedule/documnetScheduleAction";
import { connect } from "react-redux";
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
class AddNewDocumentSchedule extends Component {
  constructor() {
    super();
    this.state = {
      checked: false,
      show: true,
      topic: "",
      startDate: "",
      endDate: "",
      showData: [],
      name: [],
      type: 0,
      typeName: "",
      results: "",
      alertMsg: "",
      topicError: "",
      startError: "",
      typeError: "",
      endError: "",
      isLoading: false,
      user: localStorage(),
    };
  }

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

  validate = () => {
    let topicError = "";
    let startError = "";
    let endError = "";
    if (!this.state.topic) {
      topicError = "*Topic Type Cannot Be Blank";
    }
    if (!this.state.startDate) {
      startError = "*Start Date Type Cannot Be Blank";
    }
    if (!this.state.endDate) {
      endError = "*End Date Type Cannot Be Blank";
    }
    if (topicError || startError || endError) {
      this.setState({
        topicError,
        startError,
        endError,
      });
      return false;
    }
    return true;
  };

  handleAdd = () => {
    let topic = {
      name: this.state.topic,
      startDate: this.state.startDate,
      endDate: this.state.endDate,
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
      documentScheduleApiRequest: {
        typeId: this.state.type,
      },
      documentTopicInsertAllApiRequests: this.state.showData,
    };
    addDocumenetTopic(tp, (res) => {
      this.setState({
        alertMsg: res.data.message,
        topic: "",
      });
      Toast.fire({
        icon: "success",
        title: this.state.alertMsg,
      });
      this.props.history.push("/schedule/document_schedule");
    });
  };

  deleteData = (i) => {
    this.state.showData.splice(i, 1);
    this.setState({ showData: this.state.showData });
  };

  render() {
    return this.state.user.role === "SUPER_MENTOR" ? (
      <div>
        <h3>
          {" "}
          <i className="fas fa-calendar-alt    "></i> Add New Document Schedule
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
                <Modal.Title>Select Schedule Type</Modal.Title>
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
                    <option defaultValue={!this.state.type}>Choose...</option>
                    {this.props.projectType.map((datas, i) => (
                      <option
                        key={i}
                        value={datas.typeId}
                        onClick={() => {
                          this.getTypeName(datas.name);
                        }}
                      >
                        {datas.name} - {datas.generation.name} -{" "}
                        {datas.course.name}
                      </option>
                    ))}
                  </select>
                  <div className="text-danger ">{this.state.typeError}</div>
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
                  <div className="text-danger">{this.state.startError}</div>
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
                    className="btn btn-primary mr-2 float-right"
                  >
                    Submit
                  </button>
                ) : (
                  <Redirect to="/403"></Redirect>
                )}
              </div>
            </div>
            {/* <h3 className="mt-3">
              Document Schedule Type: {this.state.typeName}{" "}
            </h3> */}

            <h3>Data: </h3>
            <table className="table table-striped table-bordered">
              <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Topic</th>
                  <th scope="col">Start Date</th>
                  <th scope="col">End Date</th>
                  <th scope="5%">Action</th>
                </tr>
              </thead>
              <tbody>
                {this.state.showData.map((datas, i) => (
                  <tr key={i}>
                    <td>{i + 1}</td>
                    <td>{datas.name}</td>
                    <td>{datas.startDate}</td>
                    <td>{datas.endDate}</td>
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
  };
};
const mapDispatchToProps = (dispatch) => {
  return bindActionCreators({ getGeneration, getProjectType }, dispatch);
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AddNewDocumentSchedule);
