import React, { PureComponent } from "react";
import CIcon from "@coreui/icons-react";
import { MDBDataTable, MDBBadge } from "mdbreact";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import Loading from "./../loading/loading";
import {
  updateTaskByUser,
  resolvedTask,
  deleteSubTask,
  getAllTask,
} from "../../redux/actions/Task/taskAction";
import { Modal, Button, ProgressBar } from "react-bootstrap";
import Swal from "sweetalert2";
import localStorage from "../../redux/actions/Login/securityUtils/localStorages";
import { Redirect } from "react-router";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3000,
});
class ViewAllTask extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      show_projectid: this.props.match.params.show_pId,
      user: localStorage(),
      alertMsg: "",
      issue: "",
      taskId: 0,
      percentage: 0,
      isUpdate: false,
      endate: "",
      subTask: "",
      mainTask: "",
      handler: "",
      show: false,
      date: new Date(),
      percentError: "",
      msg: "",
    };
  }

  componentDidMount() {
    this.setState({
      isLoading: true,
    });
    this.props.getAllTask(this.state.show_projectid).then(() =>
      this.setState({
        isLoading: false,
      })
    );
  }

  handleChange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  handleClose = () => {
    this.setState({ show: false });
  };

  validate = () => {
    let percentError = "";

    if (!this.state.percentage) {
      percentError = "*Percentage Cannot Be Blank";
    } else if (this.state.percentage > 100 || this.state.percentage < 0) {
      percentError = "* Percentage Cannot Not Be More than 100 or Less Than 0";
    }
    if (percentError) {
      this.setState({
        percentError,
      });
      return false;
    }
    return true;
  };

  handleResolved = (id) => {
    console.log(id);
    let tt = "";
    resolvedTask(id, tt, (res) => {
      Toast.fire({
        icon: "success",
        title: "You Have Resolved Your Task",
      });
    });
    setTimeout(() => {
      this.props.getAllTask(this.state.show_projectid);
    }, 1500);
  };

  handleEdit = (id, issue, percentage, end_date, handler, sub, main, msg) => {
    this.setState({
      show: true,
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
    console.log(this.state);
  };

  updateIssue = () => {
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
        this.props.getAllTask(this.state.show_projectid);
      }, 1200);
    }
    this.setState({ show: false });
  };

  handleDelete = (id) => {
    this.props.deleteSubTask(id);
    setTimeout(() => {
      this.props.getAllTask(this.state.show_projectid);
    }, 1500);
  };

  dataTable = () => {
    let data = this.props.alltask.map((datas, i) => {
      return {
        id: datas.subTaskId,
        nameSub: datas.subTaskName,
        nameHan: datas.handler,
        nameSta: datas.startDate,
        nameEnd: datas.endDate,
        namePer: (
          <ProgressBar
            style={{ marginTop: 5 }}
            variant="success"
            now={datas.percentage}
            label={datas.percentage}
            title={datas.percentage}
          />
        ),
        namePri: (
          <div className="text-center">
            <MDBBadge color={this.getIcon(datas.priority)}>
              {" "}
              {this.getName(datas.priority)}
            </MDBBadge>
          </div>
        ),
        nameIss: (
          <p
            style={{ width: 50 }}
            className="text-truncate"
            title={datas.issue}
          >
            {datas.issue}
          </p>
        ),
        msg: (
          <p
            style={{ width: 50 }}
            className="text-truncate"
            title={datas.message}
          >
            {datas.message}
          </p>
        ),
        view: this.state.user.role !== "MEMBER" && (
          <div className="text-center">
            {this.props.authenticated ? (
              <Button
                title="Resolce Issue"
                type="button"
                className="mx-1 btn btn-sm btn-info btn-md"
                onClick={() => this.handleResolved(datas.subTaskId)}
              >
                Resolve Issue
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
                <i className="fas fa-edit "></i>{" "}
              </button>
            ) : (
              <Redirect to="/403"></Redirect>
            )}

            {this.props.authenticated ? (
              <button
                type="button"
                className="mx-1 btn btn-sm btn-danger btn-md"
                onClick={() => {
                  this.handleDelete(datas.subTaskId);
                }}
              >
                {" "}
                <i className="fas fa-trash-alt"></i>
              </button>
            ) : (
              <Redirect to="/403"></Redirect>
            )}
          </div>
        ),
      };
    });
    return data;
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
    const data = {
      columns: [
        {
          label: "#",
          field: "id", //field for data
          sort: "desc",
        },
        {
          label: "Subtask",
          field: "nameSub",
          sort: "desc",
        },
        {
          label: "Handler",
          field: "nameHan",
          sort: "desc",
        },
        {
          label: "Start-Date",
          field: "nameSta",
          sort: "desc",
        },
        {
          label: "End-Date",
          field: "nameEnd",
          sort: "desc",
        },
        {
          label: "Percentage",
          field: "namePer",
          sort: "desc",
        },
        {
          label: "Priority",
          field: "namePri",
          sort: "desc",
        },
        {
          label: "Issue",
          field: "nameIss",
          sort: "desc",
        },
        {
          label: "Message",
          field: "msg",
          sort: "desc",
        },
        {
          label: "Action",
          field: "view",
          sort: "desc",
        },
      ],
      rows: this.dataTable(),
    };
    const dataUser = {
      columns: [
        {
          label: "#",
          field: "id", //field for data
          sort: "desc",
        },
        {
          label: "Subtask",
          field: "nameSub",
          sort: "desc",
        },
        {
          label: "Handler",
          field: "nameHan",
          sort: "desc",
        },
        {
          label: "Start-Date",
          field: "nameSta",
          sort: "desc",
        },
        {
          label: "End-Date",
          field: "nameEnd",
          sort: "desc",
        },
        {
          label: "Percentage",
          field: "namePer",
          sort: "desc",
        },
        {
          label: "Priority",
          field: "namePri",
          sort: "desc",
        },
        {
          label: "Issue",
          field: "nameIss",
          sort: "desc",
        },
        {
          label: "Message",
          field: "msg",
          sort: "desc",
        },
      ],
      rows: this.dataTable(),
    };
    return (
      <>
        <Modal
          show={this.state.show}
          size="lg"
          backdrop="static"
          keyboard={false}
          onHide={this.handleClose}
        >
          <Modal.Header closeButton>
            <Modal.Title>Update My Task</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <div className="row">
              <div className="col-12">
                <div className="form-group">
                  <label>Percentage</label>
                  <input
                    onChange={this.handleChange}
                    value={this.state.percentage}
                    type="text"
                    className="form-control"
                    placeholder="Enter Task Percentage"
                    name="percentage"
                  />
                  <div className="text-danger ">{this.state.percentError}</div>
                </div>
              </div>
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
                </div>
              </div>
              <div className="col-12">
                <div className="form-group">
                  <label>Message:</label>
                  <input
                    onChange={this.handleChange}
                    value={this.state.msg}
                    type="text"
                    className="form-control"
                    name="msg"
                  />
                  <div className="text-danger ">{this.state.startError}</div>
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
                this.state.user.role === "SUPER_MENTOR"
                  ? this.updateIssue()
                  : this.updateSubTask();
              }}
            >
              Update
            </Button>
          </Modal.Footer>
        </Modal>
        <h3>
          {" "}
          <CIcon name="cil-list" height={25} className="mb-2" /> View All Task
        </h3>
        <div className="clearfix"> </div>
        <br />
        {this.props.alltask.length < 1 ? (
          <Loading height="60vh" />
        ) : (
          <>
            {this.state.user.role !== "MEMBER" ? (
              <MDBDataTable striped bordered hover data={data} btn />
            ) : (
              <MDBDataTable striped bordered hover data={dataUser} btn />
            )}
          </>
        )}
      </>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    alltask: state.taskReducer.alltask,
    authenticated: state.authenticatedReducer.authenticated,
  };
};

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getAllTask,
      deleteSubTask,
    },
    dispatch
  );
};
export default connect(mapStateToProps, mapDispatchToProps)(ViewAllTask);
