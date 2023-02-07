import React, { Component } from "react";
import "./Document.css";
import { Link, Redirect } from "react-router-dom";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";
import { getProjectType } from "../../../redux/actions/Schedule/scheduleType/scheduleTypeActions";
import {
  getDocumentSchedule,
  getDocumentScheduleOne,
  updateDocumentSchedule,
  deleteDocumnetSchedule,
  updateDocumentStatus,
  getDocumentScheduleUser,
} from "../../../redux/actions/Schedule/documentSchedule/documnetScheduleAction";
import Swal from "sweetalert2";
import { Modal, Button } from "react-bootstrap";
import localStorage from "../../../redux/actions/Login/securityUtils/localStorages";
import { getUserProject } from "../../../redux/actions/Dashboard/dashboardAction";
import Loading from "../../loading/loading";

//data table
import { MDBDataTable } from "mdbreact";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3000,
});
class NewDocumentSchedule extends Component {
  constructor(props) {
    super();
    this.state = {
      show: false,
      schedule: 0,
      isUpdate: false,
      updateId: "",
      status: false,
      alertMsg: "",
      isLoading: true,
      user: localStorage(),
      documentShow: "",
    };
  }

  // arrow function for loop data new tbl
  dataTable = () => {
    let data;
    data = this.props.documentSchedule.map((datas, i) => {
      return {
        id: i + 1,
        name: datas.scheduleType.name,
        nameGen: datas.scheduleType.generation.name,
        nameCou: datas.scheduleType.course.name,
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
            <Link to={`/schedule/view_document_schedule/${datas.id}`}>
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
                      datas.scheduleType.typeId,
                      datas.status
                    );
                  }}
                >
                  <i className="fas fa-edit "></i>{" "}
                </button>

                <button
                  type="button"
                  className="mx-1 btn btn-sm btn-danger"
                  onClick={() => {
                    this.hanldeDelete(datas.id);
                  }}
                >
                  {" "}
                  <i className="fas fa-trash-alt    "></i>
                </button>
              </>
            ) : (
              <span></span>
            )}
          </div>
        ),
      };
    });
    return data;
  };

  showDataSuperMentor() {
    this.props.getProjectType();
    this.props.getDocumentSchedule().then(() =>
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
    this.setState({ show: false });
  };

  handleShow = () => {
    this.setState({ show: true });
  };

  updateScheduleType = () => {
    const schedule = {
      typeId: this.state.schedule,
    };

    const staus = {
      status: this.state.status,
    };

    if (this.state.isUpdate) {
      updateDocumentStatus(this.state.updateId, staus, (res) => {
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
      updateDocumentSchedule(this.state.updateId, schedule, (res) => {
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
        this.props.getDocumentSchedule();
      }, 1200);
    }
    this.setState({ show: false });
  };

  handleEdit = (id, typeId, status) => {
    this.setState({
      show: true,
      updateId: id,
      schedule: typeId,
      isUpdate: true,
      status: status,
    });
    console.log(this.state);
  };

  hanldeDelete = (id) => {
    this.props.deleteDocumnetSchedule(id);
    setTimeout(() => {
      this.props.getDocumentSchedule();
      this.props.getProjectType();
    }, 3500);
  };

  render() {
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
            <Modal.Title>Update Document Schedule Type</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <div className="form-group">
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
                    {datas.name}-{datas.generation.name}-{datas.course.name}
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
                this.updateScheduleType();
              }}
            >
              Update
            </Button>
          </Modal.Footer>
        </Modal>

        <h3>
          {" "}
          <i className="fas fa-calendar-alt    "></i> New Document Schedule
        </h3>

        {this.state.user.role === "SUPER_MENTOR" ? (
          <>
            {this.props.documentSchedule.length < 1 ? (
              ""
            ) : (
              <>
                <Link
                  type="submit"
                  className="btn btn-primary float-right"
                  to="/schedule/add_new_document_schedule"
                >
                  Add New
                </Link>
                <br />
              </>
            )}
          </>
        ) : (
          <span></span>
        )}

        <br />
        {this.props.documentSchedule.length < 1 ? (
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
    documentSchedule: state.documentScheduleReducer.documentSchedule,
    projectType: state.projectTypeReducer.projectType,
    userProject: state.dashboardReducer.userProject,
    documentScheduleUser: state.documentScheduleReducer.documentScheduleUser,
    authenticated: state.authenticatedReducer.authenticated,
  };
};

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getDocumentSchedule,
      getDocumentScheduleOne,
      getProjectType,
      deleteDocumnetSchedule,
      updateDocumentStatus,
      getUserProject,
      getDocumentScheduleUser,
    },
    dispatch
  );
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NewDocumentSchedule);
