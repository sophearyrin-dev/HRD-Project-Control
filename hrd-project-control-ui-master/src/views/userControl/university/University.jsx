import React, { PureComponent } from "../../../../node_modules/react";

import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import {
  getUniversity,
  addUniversity,
  deleteUniversity,
  editUniversity,
} from "../../../redux/actions/University/universityAction";
import Loading from "./../../loading/loading";
import { MDBDataTable } from "mdbreact";
import Swal from "sweetalert2";

import localStorage from "../../../redux/actions/Login/securityUtils/localStorages";
import { Redirect } from "react-router-dom";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3000,
});

class University extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      alertMsg: "",
      universityError: "",
      isUpdate: false,
      updateId: "",
      university: "",
      status: true,
      isLoading: true,
      user: localStorage(),
    };
  }

  componentDidMount() {
    this.state.user.role === "SUPER_MENTOR" &&
      this.props.getUniversity().then(() =>
        this.setState({
          isLoading: false,
        })
      );
  }
  dataTable = () => {
    let data = this.props.university.map((datas, i) => {
      return {
        id: i + 1,
        name: datas.name,

        view: (
          <div className="text-center">
            <button
              type="button"
              className="mx-1 btn btn-sm btn-primary btn-md"
              onClick={() => this.handleEdit(datas.universityId, datas.name)}
            >
              <i className="fas fa-edit "></i>{" "}
            </button>
            <button
              onClick={() => {
                this.handleDelete(datas.universityId);
              }}
              type="button"
              className="mx-1 btn btn-sm btn-danger btn-md"
            >
              {" "}
              <i className="fas fa-trash-alt    "></i>
            </button>
          </div>
        ),
      };
    });
    return data;
  };
  handleDelete = (id) => {
    this.props.deleteUniversity(id);
    setTimeout(() => {
      this.props.getUniversity();
    });
  };

  handleChange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };
  validate = () => {
    let universityError = "";

    if (!this.state.university) {
      universityError = "*University field Cannot be Blank";
    }
    if (universityError) {
      this.setState({
        universityError,
      });
      return false;
    }
    return true;
  };

  handleSubmit = () => {
    const university = {
      name: this.state.university,
    };
    if (this.validate()) {
      if (this.state.isUpdate) {
        editUniversity(this.state.updateId, university, (res) => {
          this.props.getUniversity();
          this.setState({
            alertMsg: res.data.message,
            isUpdate: false,
            university: "",
            updateId: "",
          });
          Toast.fire({
            icon: "success",
            title: this.state.alertMsg,
          });
        });
      } else {
        addUniversity(university, (res) => {
          this.props.getUniversity();
          this.setState({
            alertIcon: "success",
            alertMsg: res.data.message,
            university: "",
          });
          Toast.fire({
            icon: "success",
            title: this.state.alertMsg,
          });
        });
      }
      this.setState({ universityError: "" });
    }
  };

  handleEdit = (universityId, name) => {
    this.setState({
      updateId: universityId,
      university: name, //*not use (name)
      isUpdate: true,
    });
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
          label: "University",
          field: "name",
          sort: "asc",
        },
        {
          label: "Action",
          field: "view",
        },
      ],
      rows: this.dataTable(),
    };

    if (this.props.university.length === 0) {
      return <Loading height="84vh" />;
    } else {
      return this.state.user.role === "SUPER_MENTOR" ? (
        <div>
          <h3>
            <i class="fa fa-university" aria-hidden="true"></i>
            University
          </h3>
          <div className="row">
            <div className="form-group col-6 ">
              <label>University</label>
              <input
                onChange={this.handleChange}
                value={this.state.university}
                type="text"
                className="form-control"
                placeholder="Enter University"
                name="university"
                autoComplete="off"
              />
              <div className="text-danger ">{this.state.universityError}</div>
            </div>
            <div className="col-auto" style={{ marginTop: "29px" }}>
              <button
                type="submit"
                onClick={this.handleSubmit}
                className={
                  this.state.updateId ? "btn btn-warning" : "btn btn-primary"
                }
              >
                {this.state.updateId ? "Update" : "Submit"}
              </button>
            </div>
          </div>
          <div className="clearfix"> </div>
          <MDBDataTable striped bordered hover data={data} btn />
        </div>
      ) : (
        <Redirect to="/403"></Redirect>
      );
    }
  }
}

const mapStateToProps = (state) => {
  return {
    university: state.universityReducer.university,
  };
};

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getUniversity,
      deleteUniversity,
    },
    dispatch
  );
};
export default connect(mapStateToProps, mapDispatchToProps)(University);
// export default University;
