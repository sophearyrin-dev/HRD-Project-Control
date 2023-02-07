import React, { Component } from "react";
import { Link, Redirect } from "react-router-dom";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";
import { getProjectType } from "../../../redux/actions/Schedule/scheduleType/scheduleTypeActions";
import {
  getProjectSchedule,
  updateProjectSchedule,
  updateProjectStatus,
  deleteProjectSchedule,
  getProjectScheduleUser,
} from "../../../redux/actions/Schedule/projectSchedule/projectScheduleAction";
import Swal from "sweetalert2";
import { Modal, Button, Tab, Tabs } from "react-bootstrap";
import DayPicker, { DateUtils } from "react-day-picker";
import "./Project.css";
import "react-day-picker/lib/style.css";
import localStorage from "../../../redux/actions/Login/securityUtils/localStorages";
import { getUserProject } from "../../../redux/actions/Dashboard/dashboardAction";
import Loading from "./../../loading/loading";
//data table
import { MDBDataTable } from "mdbreact";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3000,
});
class NewProjectSchedule extends Component {
  constructor(props) {
    super();
    this.state = {
      showGroupMeeting: false,
      show: false,
      schedule: 0,
      isUpdate: false,
      updateId: "",
      status: false,
      alertMsg: "",
      isLoading: true,
      myTest: "",
      selectedDays: [],
      newSelectedDays: [],
      user: localStorage(),
      documentShow: "",
      testing: new Date(),
    };
  }

  // arrow function for loop data new tbl
  dataTable = () => {
    let data;
    data = this.props.projectSchedule.map((datas, i) => {
      return {
        id: i + 1,
        name: datas.type.name,
        nameGen: datas.type.generation.name,
        nameCou: datas.type.course.name,
        nameSta: (
          <div className="text-center">
            <span
              className={
                datas.status === true
                  ? "badge bg-success text-white p-2"
                  : "badge bg-danger text-white p-2"
              }
            >
              <span className=" p2">
                {datas.status === true ? "SHOW" : "HIDDEN"}
              </span>
            </span>
          </div>
        ),
        view: (
          <div className="text-center">
            <Link to={`/schedule/view_project_schedule/${datas.id}`}>
              <button type="button" className="mx-1 btn btn-sm btn-success">
                <i className="fas fa-eye    "></i>
              </button>
            </Link>

            {this.state.user.role === "SUPER_MENTOR" ? (
              <>
                <button
                  type="button"
                  className="mx-1 btn btn-sm btn-primary"
                  onClick={() => {
                    this.handleEdit(
                      datas.id,
                      datas.type.typeId,
                      datas.status,
                      datas.groupMeeting
                    );
                  }}
                >
                  <i className="fas fa-edit "></i>{" "}
                </button>
                <button
                  type="button"
                  className="mx-1 btn btn-sm btn-danger"
                  onClick={() => {
                    this.handleDelete(datas.id);
                  }}
                >
                  {" "}
                  <i className="fas fa-trash-alt    "></i>
                </button>
              </>
            ) : (
              <span></span>
            )}
            <button
              onClick={() => {
                this.showGroupMeetin(datas.groupMeeting);
              }}
              type="button"
              className="mx-1 btn btn-sm btn-info btn-md"
            >
              {" "}
              <i className="fas fa-calendar    "></i>
            </button>
          </div>
        ),
      };
    });
    return data;
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

  showDataSuperMentor() {
    this.props.getProjectType();
    this.props.getProjectSchedule().then(() =>
      this.setState({
        isLoading: false,
      })
    );
  }

  componentDidMount() {
    this.showDataSuperMentor();
  }

  handleChange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  handleClose = () => {
    this.setState({ show: false, showGroupMeeting: false });
  };

  handleShow = () => {
    this.setState({ show: true, showGroupMeeting: true });
  };

  updateScheduleType = () => {
    const schedule = {
      typeId: this.state.schedule,
      groupMeeting: this.state.selectedDays,
    };
    const staus = {
      status: this.state.status,
    };
    if (this.state.isUpdate) {
      updateProjectStatus(this.state.updateId, staus, (res) => {
        this.setState({
          alertMsg: res.data.message,
          isUpdate: false,
          schedule: 0,
          updateId: "",
        });
        Toast.fire({
          icon: "success",
          title: this.state.alertMsg,
        });
      });
      updateProjectSchedule(this.state.updateId, schedule, (res) => {
        this.setState({
          alertMsg: res.data.message,
          isUpdate: false,
          schedule: 0,
          updateId: "",
        });
        Toast.fire({
          icon: "success",
          title: this.state.alertMsg,
        });
      });

      setTimeout(() => {
        this.props.getProjectType();
        this.props.getProjectSchedule();
      }, 1200);
    }
    this.setState({ show: false });
  };

  newUpdateScheduleType = () => {
    const schedule = {
      typeId: this.state.schedule,
      groupMeeting: this.state.selectedDays.toString(),
    };
    const staus = {
      status: this.state.status,
    };
    if (this.state.isUpdate) {
      updateProjectStatus(this.state.updateId, staus, (res) => {
        this.setState({
          alertMsg: res.data.message,
          isUpdate: false,
          schedule: 0,
          updateId: "",
        });
        Toast.fire({
          icon: "success",
          title: this.state.alertMsg,
        });
      });
      updateProjectSchedule(this.state.updateId, schedule, (res) => {
        this.setState({
          alertMsg: res.data.message,
          isUpdate: false,
          schedule: 0,
          updateId: "",
          newSelectedDays: "",
        });
        Toast.fire({
          icon: "success",
          title: this.state.alertMsg,
        });
      });

      setTimeout(() => {
        this.props.getProjectType();
        this.props.getProjectSchedule();
      }, 1500);
    }
    this.setState({ show: false });
  };

  handleEdit = (id, typeId, status, meeting) => {
    let res = meeting.split(",");
    let ar = [];
    for (let index = 0; index < res.length; index++) {
      ar.push(new Date(res[index]));
    }
    this.setState({
      show: true,
      updateId: id,
      schedule: typeId,
      isUpdate: true,
      status: status,
      selectedDays: ar,
    });
    console.log(this.state.selectedDays);
  };

  handleDelete = (id) => {
    this.props.deleteProjectSchedule(id);
    setTimeout(() => {
      this.props.getProjectSchedule();
      this.props.getProjectType();
    }, 1500);
  };

  showGroupMeetin = (meeting) => {
    let res = meeting.split(",");
    let ar = [];
    for (let index = 0; index < res.length; index++) {
      ar.push(new Date(res[index]));
    }
    this.setState({
      showGroupMeeting: true,
      selectedDays: ar,
    });
  };

  render() {
    console.log(this.state.selectedDays);
    const data = {
      columns: [
        {
          label: "#",
          field: "id", //field for data
          sort: "asc",
        },
        {
          label: "Schedule Type",
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
          label: "Status",
          field: "nameSta",
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
    return this.props.authenticated ? (
      <>
        <Modal
          show={this.state.show}
          size="md"
          backdrop="static"
          keyboard={false}
          onHide={this.handleClose}
        >
          <Modal.Header closeButton>
            <Modal.Title>
              Update Project Schedule Type / Group Meeting
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Tabs defaultActiveKey="scheduleType" id="uncontrolled-tab-example">
              <Tab eventKey="scheduleType" title="Schedule Type / Status">
                <div className="form-group mt-2">
                  <label>Schedule Type</label>
                  <select
                    className="custom-select "
                    value={this.state.schedule}
                    onChange={this.handleChange}
                    name="schedule"
                  >
                    <option defaultValue="">Choose...</option>
                    {this.props.projectType.map((datas, i) => (
                      <option key={i} value={datas.typeId}>
                        {datas.name} - {datas.generation.name} -{" "}
                        {datas.course.name}
                      </option>
                    ))}
                  </select>
                </div>
                <div className="form-group">
                  <label>Status</label>
                  <select
                    className="custom-select "
                    value={this.state.status}
                    onChange={this.handleChange}
                    name="status"
                  >
                    <option defaultValue="">Choose...</option>
                    <option value="true">True</option>
                    <option value="false">False</option>
                  </select>
                  <span className="text-danger">
                    **NOTE : IF STATUS EQUAL FALSE STUDENT CANT VIEW
                  </span>
                </div>
                <hr />
                <div className="float-right">
                  {" "}
                  <Button
                    variant="secondary"
                    className="btn-sm  mx-1"
                    onClick={this.handleClose}
                  >
                    Close
                  </Button>
                  <Button
                    className="btn-sm  mx-1"
                    variant="warning"
                    onClick={() => {
                      this.updateScheduleType();
                    }}
                  >
                    Update
                  </Button>
                </div>
              </Tab>
              <Tab eventKey="groupMeeting" title="Group Meeting">
                <div className="text-center">
                  <DayPicker
                    selectedDays={this.state.selectedDays}
                    onDayClick={this.handleDayClick}
                  />
                </div>
                <hr />
                <div className="float-right">
                  {" "}
                  <Button
                    variant="secondary"
                    className="btn-sm  mx-1"
                    onClick={this.handleClose}
                  >
                    Close
                  </Button>
                  <Button
                    className="btn-sm  mx-1"
                    variant="warning"
                    onClick={() => {
                      this.newUpdateScheduleType();
                    }}
                  >
                    Update
                  </Button>
                </div>
              </Tab>
            </Tabs>
          </Modal.Body>
        </Modal>

        <h3>
          {" "}
          <i className="fas fa-calendar-alt    "></i> New Project Schedule
        </h3>

        {this.state.user.role === "SUPER_MENTOR" ? (
          <>
            {this.props.projectSchedule.length < 1 ? (
              ""
            ) : (
              <Link
                type="submit"
                className="btn btn-primary float-right"
                to="/schedule/add_new_project_schedule"
              >
                Add New
              </Link>
            )}
            <br />
          </>
        ) : (
          <span></span>
        )}

        <br />
        <Modal
          show={this.state.showGroupMeeting}
          size="md"
          backdrop="static"
          keyboard={false}
          onHide={this.handleClose}
        >
          <Modal.Header closeButton>
            <Modal.Title>Group Meeting</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <div className="text-center">
              <DayPicker
                value={this.state.selectedDays}
                selectedDays={this.state.selectedDays}
                onDayClick={this.handleDayClick}
              />
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
          </Modal.Footer>
        </Modal>
        {this.props.projectSchedule.length < 1 ? (
          <Loading height="65vh" />
        ) : (
          <MDBDataTable striped bordered hover data={data} btn />
        )}
      </>
    ) : (
      <Redirect to="/403"></Redirect>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    projectSchedule: state.projectScheduleReducer.projectSchedule,
    projectType: state.projectTypeReducer.projectType,
    projectScheduleUser: state.projectScheduleReducer.projectScheduleUser,
    userProject: state.dashboardReducer.userProject,
    authenticated: state.authenticatedReducer.authenticated,
  };
};

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getProjectSchedule,
      updateProjectSchedule,
      getProjectType,
      updateProjectStatus,
      deleteProjectSchedule,
      getUserProject,
      getProjectScheduleUser,
    },
    dispatch
  );
};

export default connect(mapStateToProps, mapDispatchToProps)(NewProjectSchedule);
