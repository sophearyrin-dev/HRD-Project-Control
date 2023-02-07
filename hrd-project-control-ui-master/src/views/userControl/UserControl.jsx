import React, { PureComponent } from "react";
import {
  getUser,
  deleteUser,
} from "../../redux/actions/UserControl/usercontrolAction";
import { getGeneration } from "../../redux/actions/Generation/generationAction";
import { getUniversity } from "../../redux/actions/University/universityAction";
import { connect } from "react-redux";
import { Link, Redirect } from "react-router-dom";
import { bindActionCreators } from "redux";
import "./UserControl.css";
import Loading from "./../loading/loading";
import { MDBDataTable } from "mdbreact";
import localStorage from "../../redux/actions/Login/securityUtils/localStorages";

class UserControl extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      isLoading: true,
      user: localStorage(),
    };
  }
  handleChange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };
  componentDidMount() {
    this.state.user.role === "SUPER_MENTOR" &&
      this.props.getUser().then(() =>
        this.setState({
          isLoading: false,
        })
      );
    this.props.getGeneration();
    this.props.getUniversity();
    this.props.getUser().then(() =>
      this.setState({
        isLoading: false,
      })
    );
  }
  handleDelete = (id) => {
    this.props.deleteUser(id);
    setTimeout(() => {
      this.props.getUser();
    });
  };
  dataTable = () => {
    let data = this.props.user.map((datas, i) => {
      return {
        id: i + 1,
        username: datas.username, //name is a field name
        nameGen: datas.generation.name,
        gender: datas.gender,
        role: datas.role.name,
        university: datas.university.name,
        view: (
          <div className="text-center">
            <Link to={`/user_control/update_user/${datas.userId}`}>
              <button
                type="button"
                className="mx-1 btn btn-sm btn-primary btn-md"
              >
                <i className="fas fa-edit "></i>{" "}
              </button>
            </Link>

            <button
              onClick={() => {
                this.handleDelete(datas.userId);
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

  render() {
    const data = {
      columns: [
        {
          label: "#",
          field: "id", //field for data
          sort: "desc",
        },
        {
          label: "Name",
          field: "username",
          sort: "desc",
        },
        {
          label: "Generation",
          field: "nameGen",
          sort: "desc",
        },
        {
          label: "Gender",
          field: "gender",
          sort: "desc",
        },
        {
          label: "Role",
          field: "role",
          sort: "desc",
        },
        {
          label: "University",
          field: "university",
          sort: "desc",
        },
        {
          label: "Action",
          field: "view",
        },
      ],

      rows: this.dataTable(),
    };
    if (this.props.user.length === 0) {
      return <Loading height="70vh" />;
    } else {
      return this.state.user.role === "SUPER_MENTOR" ? (
        <div>
          <h3>
            {" "}
            <i className="fas fa-user   "></i> User Control
          </h3>
          <Link
            type="submit"
            className="btn btn-primary float-right"
            to="/user_control/new_user"
          >
            Add New
          </Link>

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
    user: state.userReducer.user,
  };
};
const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getUser,
      deleteUser,
      getGeneration,
      getUniversity,
    },
    dispatch
  );
};
export default connect(mapStateToProps, mapDispatchToProps)(UserControl);
