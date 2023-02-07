import React, { PureComponent } from "react";
import CIcon from "@coreui/icons-react";
import { MDBDataTable, MDBBadge } from "mdbreact";
import {
  getMyTask,
  updateTaskByUser,
  postDailyReport,
  resolvedTask,
  deleteSubTask,
} from "../../redux/actions/Task/taskAction";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import localStorage from "../../redux/actions/Login/securityUtils/localStorages";
import { Modal, Button, ProgressBar } from "react-bootstrap";
import Swal from "sweetalert2";
import Moment from "moment";
import Loading from "./../loading/loading";
import { Redirect } from "react-router-dom";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3000,
});
class ViewMyTask extends PureComponent {
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

      loading: true,
    };
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
    let tt = "";
    resolvedTask(id, tt, (res) => {
      Toast.fire({
        icon: "success",
        title: "You Have Resolved Your Task",
      });
    });
    setTimeout(() => {
      this.props.getMyTask(this.state.user.id, this.state.show_projectid);
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
        this.props.getMyTask(this.state.user.id, this.state.show_projectid);
      }, 1200);
    }
    this.setState({ show: false });
  };

  updateSubTask = () => {
    const subTask = {
      issue: this.state.issue,
      percentage: this.state.percentage,
      message: this.state.msg,
    };

    let curDate = Moment(this.state.date).format("YYYY-MM-DD");
    const dailyReport = {
      deadline: this.state.endate,
      current_date: curDate,
      developer_name: this.state.handler,
      main_task: this.state.mainTask,
      message: this.state.msg,
      progress_percentage: this.state.percentage,
      project_id: this.state.show_projectid,
      sub_task: this.state.subTask,
    };

    postDailyReport(dailyReport, (res) => {
      this.setState({
        alertMsg: res.data.message,
      });
      Toast.fire({
        icon: "success",
        title: this.state.alertMsg,
      });
      this.props.getMyTask(this.state.user.id, this.state.show_projectid);
    });
    if (this.validate()) {
      if (this.state.isUpdate) {
        updateTaskByUser(this.state.taskId, subTask, (res) => {
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
          this.props.getMyTask(this.state.user.id, this.state.show_projectid);
        }, 1200);
      }
      this.setState({ show: false });
    }
  };

  componentDidMount() {
    this.props
      .getMyTask(this.state.user.id, this.state.show_projectid)
      .then(() => {
        this.setState({
          loading: false,
        });
      });
  }

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

  handleDelete = (id) => {
    this.props.deleteSubTask(id);
    setTimeout(() => {
      this.props.getMyTask(this.state.user.id, this.state.show_projectid);
    }, 1500);
  };

  dataTable = () => {
    let data = this.props.mytask.map((datas, i) => {
      return {
        id: i + 1,
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
        view: (
          <div className="text-center">
            <Button
              title="Resolce Issue"
              type="button"
              className="mx-1 btn btn-sm btn-info btn-md"
              onClick={() => this.handleResolved(datas.subTaskId)}
            >
              Resolve Issue
            </Button>
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
            {this.state.user.role !== "MEMBER" &&
              (this.props.authenticated ? (
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
              ))}
          </div>
        ),
      };
    });
    return data;
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
          label: "Subtask",
          field: "nameSub",
          sort: "asc",
        },
        {
          label: "Handler",
          field: "nameHan",
          sort: "asc",
        },
        {
          label: "Start-Date",
          field: "nameSta",
          sort: "asc",
        },
        {
          label: "End-Date",
          field: "nameEnd",
          sort: "asc",
        },
        {
          label: "Percentage",
          field: "namePer",
          sort: "asc",
        },
        {
          label: "Priority",
          field: "namePri",
          sort: "asc",
        },
        {
          label: "Issue",
          field: "nameIss",
          sort: "asc",
        },
        {
          label: "Message",
          field: "msg",
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
          <CIcon name="cil-list" height={25} className="mb-2" /> View My Task
        </h3>

        <div className="clearfix"> </div>
        <br />

        {this.props.mytask.length < 1 ? (
          <>
            {this.state.loading && <Loading height="60vh" />}
            <div className="row">
              <div
                style={{
                  left: "50%",
                  top: "55%",
                  position: "absolute",
                }}
                hidden={this.state.loading}
              >
                <h4> There's No Task For You</h4>
              </div>
            </div>
          </>
        ) : (
          <MDBDataTable striped bordered hover data={data} btn />
        )}
      </>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    mytask: state.taskReducer.mytask,
    authenticated: state.authenticatedReducer.authenticated,
  };
};

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getMyTask,
      deleteSubTask,
    },
    dispatch
  );
};
export default connect(mapStateToProps, mapDispatchToProps)(ViewMyTask);
