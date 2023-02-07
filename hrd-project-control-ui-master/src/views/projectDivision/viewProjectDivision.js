import React, { Component } from "react";
import {
  getProjectDivision,
  getProjectDivisionFeatObj,
} from "../../redux/actions/ProjectDivision/projectdivisionAction";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import Loading from "../loading/loading";

class ViewAssignProject extends Component {
  constructor(props) {
    super();
    this.state = {
      isLoading: true,
    };
  }

  componentDidMount() {
    const {
      match: { params },
    } = this.props;

    this.props.getProjectDivisionFeatObj(params.id);
    this.props.getProjectDivision(params.id).then(() => {
      this.setState({
        isLoading: false,
      });
    });
  }

  render() {
    if (
      this.props.projectDivision.length < 1 &&
      this.props.divisionFeature === undefined
    ) {
      return (
        <div
          className="text-center"
          style={{ top: "50%", left: "40%", position: "absolute" }}
        >
          <h2> Please Assign The Project Before View The Project Division</h2>
        </div>
      );
    } else {
      return (
        <div>
          {this.props.divisionFeature === undefined ? (
            <span></span>
          ) : (
            <>
              <h3>
                <i height={25} className="fab fa-product-hunt mb-2"></i>{" "}
                {this.props.divisionFeature.map((datas, i) => (
                  <span>{datas.name}</span>
                ))}
              </h3>
            </>
          )}

          {this.state.isLoading ? (
            <Loading height="75vh" />
          ) : (
            <>
              <table className="table table-striped table-bordered">
                <thead>
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Gender</th>
                    <th scope="col">University</th>
                    <th scope="col">Remark</th>
                  </tr>
                </thead>
                <tbody>
                  {this.props.projectDivision.map((datas, i) => (
                    <tr key={i + 1}>
                      <th scope="col">{i + 1}</th>
                      <td>{datas.user.username}</td>
                      <td>{datas.user.gender}</td>
                      <td>{datas.user.university.name}</td>
                      <td>{datas.user.role.name}</td>
                    </tr>
                  ))}
                </tbody>
                <tfoot>
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Gender</th>
                    <th scope="col">University</th>
                    <th scope="col">Remark</th>
                  </tr>
                </tfoot>
              </table>

              <div className="row mt-3">
                {this.props.divisionFeature === undefined ? (
                  <span></span>
                ) : (
                  <>
                    <div className="col-6">
                      <h4>
                        <i className="fas fa-object-group    "> Objectives</i>
                      </h4>
                      {this.props.divisionFeature.map((datas, i) => (
                        <h5>{datas.objective}</h5>
                      ))}
                    </div>
                    <div className="col-6">
                      <h4>
                        <i className="fas fa-list"> Features</i>
                      </h4>
                      {this.props.divisionFeature.map((datas, i) => (
                        <p>{datas.feature}</p>
                      ))}
                    </div>
                  </>
                )}
              </div>
            </>
          )}
        </div>
      );
    }
  }
}

const mapStateToProps = (state) => {
  return {
    projectDivision: state.projectDivisionReducer.projectDivision,
    divisionFeature: state.projectDivisionReducer.projectDivisionFeature,
    project: state.projectReducer.project,
  };
};
const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getProjectDivision,
      getProjectDivisionFeatObj,
    },
    dispatch
  );
};

export default connect(mapStateToProps, mapDispatchToProps)(ViewAssignProject);
