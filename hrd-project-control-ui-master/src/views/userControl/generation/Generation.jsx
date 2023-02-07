import React, { PureComponent } from "../../../../node_modules/react";
import {
  getGeneration,
  deleteGeneration,
  addGeneration,
  editGeneration,
} from "../../../redux/actions/Generation/generationAction";
import Swal from "sweetalert2";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import "./generation.css";
import Loading from "./../../loading/loading";
import { MDBDataTable } from "mdbreact";

import localStorage from "../../../redux/actions/Login/securityUtils/localStorages";
import { Redirect } from "react-router-dom";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3000,
});

class Generation extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      alertMsg: "",
      generationError: "",
      isUpdate: false,
      updateId: "",
      generation: "",
      status: true,
      isLoading: true,
      user: localStorage(),
    };
  }

  componentDidMount() {
    this.state.user.role === "SUPER_MENTOR" &&
      this.props.getGeneration().then(() =>
        this.setState({
          isLoading: false,
        })
      );
  }

  dataTable = () => {
    let data = this.props.generation.map((datas, i) => {
      return {
        id: i + 1,
        name: datas.name,

        view: (
          <div className="text-center">
            <button
              type="button"
              className="mx-1 btn btn-sm btn-primary btn-md"
              onClick={() => this.handleEdit(datas.generationId, datas.name)}
            >
              <i className="fas fa-edit "></i>{" "}
            </button>

            <button
              onClick={() => {
                this.handleDelete(datas.generationId);
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
    this.props.deleteGeneration(id);
    setTimeout(() => {
      this.props.getGeneration();
    });
  };

  handleChange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  validate = () => {
    let generationError = "";

    if (!this.state.generation) {
      generationError = "*Generation Cannot be Blank";
    }
    if (generationError) {
      this.setState({
        generationError,
      });
      return false;
    }
    return true;
  };

  handleSubmit = () => {
    const generation = {
      name: this.state.generation,
    };
    if (this.validate()) {
      if (this.state.isUpdate) {
        editGeneration(this.state.updateId, generation, (res) => {
          this.props.getGeneration();
          this.setState({
            alertMsg: res.data.message,
            isUpdate: false,
            generation: "",
            updateId: "",
          });
          Toast.fire({
            icon: "success",
            title: this.state.alertMsg,
          });
        });
      } else {
        addGeneration(generation, (res) => {
          this.props.getGeneration();
          this.setState({
            alertIcon: "success",
            alertMsg: res.data.message,
            generation: "",
          });
          Toast.fire({
            icon: "success",
            title: this.state.alertMsg,
          });
        });
      }
      this.setState({ generationError: "" });
    }
  };

  handleEdit = (generationId, name) => {
    this.setState({
      updateId: generationId,
      generation: name, //*not use (name)
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
          label: "Generation",
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

    if (this.props.generation.length === 0) {
      return <Loading height="84vh" />;
    } else {
      return this.state.user.role === "SUPER_MENTOR" ? (
        <div>
          <h3>
            <i className="fab fa-gofore"> </i> Generation
          </h3>
          <div className="row">
            <div className="form-group col-6 ">
              <label>Generation</label>
              <input
                onChange={this.handleChange}
                value={this.state.generation}
                type="text"
                className="form-control"
                placeholder="Enter Generation"
                name="generation"
                autoComplete="off"
              />
              <div className="text-danger ">{this.state.generationError}</div>
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
    generation: state.generationReducer.generation,
    authenticated: state.authenticatedReducer.authenticated,
  };
};

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getGeneration,
      deleteGeneration,
    },
    dispatch
  );
};
// export default Generation;
export default connect(mapStateToProps, mapDispatchToProps)(Generation);
