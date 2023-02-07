import React, { PureComponent } from "react";

import { Link } from "react-router-dom";
import { bindActionCreators } from "redux";
import { getCourse } from "../../redux/actions/Course/courseAction";
import { getGeneration } from "../../redux/actions/Generation/generationAction";
import { getProjectFilter } from "../../redux/actions/DailyReport/dailyreportAction";
import { connect } from "react-redux";
import Loading from "./../loading/loading";
import localStorages from "../../redux/actions/Login/securityUtils/localStorages";
import { Redirect } from "react-router-dom";

class AssignProject extends PureComponent {
  constructor(props) {
    super();
    this.state = {
      generation: 0,
      course: 0,
      generationId: "",
      courseId: "",
      isLoading: false,
      isNull: true,
      user: localStorages(),
      empty: true,
    };
  }

  componentDidMount() {
    this.state.user.role === "SUPER_MENTOR" && this.props.getGeneration();
    this.props.getCourse();
  }

  handleChange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  handleFilter = () => {
    this.setState({
      isLoading: true,
      isNull: false,
    });
    this.props
      .getProjectFilter(this.state.course, this.state.generation)
      .then(() =>
        this.setState({
          isLoading: false,
          isNull: false,
          empty: false,
        })
      );
  };

  render() {
    return this.state.user.role === "SUPER_MENTOR" ? (
      <>
        <h3>
          {" "}
          <i height={25} className="fab fa-product-hunt mb-2"></i> Assign
          Project
        </h3>
        <div>
          <div style={{ marginBottom: "20px" }}>
            <div>
              {" "}
              <div className="form-row float-right">
                <div className="col-auto ">
                  <label>Generation</label>
                  <select
                    className="custom-select shadow"
                    value={this.state.generation}
                    onChange={this.handleChange}
                    name="generation"
                  >
                    <option defaultValue={this.state.generation}>
                      Choose...
                    </option>
                    {this.props.generation.map((datas, i) => (
                      <option key={i} value={datas.generationId}>
                        {datas.name}
                      </option>
                    ))}
                  </select>
                </div>
                <div className="col-auto">
                  <label>Course</label>
                  <select
                    className="custom-select shadow"
                    value={this.state.course}
                    onChange={this.handleChange}
                    name="course"
                  >
                    <option defaultValue={this.state.course}>Choose...</option>
                    {this.props.course.map((datas, i) => (
                      <option key={i} value={datas.courseId}>
                        {datas.name}
                      </option>
                    ))}
                  </select>
                </div>
                <div className="col-auto" style={{ marginTop: "30px" }}>
                  <button
                    type="filter"
                    onClick={this.handleFilter}
                    className="btn btn-primary shadow"
                  >
                    Filter
                  </button>
                </div>
              </div>
            </div>
            <div className="clearfix"> </div>
          </div>

          {this.props.dailyreport.length < 1 ? (
            <>
              {this.state.isLoading && <Loading height="60vh" />}
              <div className="row">
                {this.state.isNull && (
                  <div
                    style={{
                      left: "50%",
                      top: "55%",
                      position: "absolute",
                    }}
                  >
                    <h4>Please Filter To Get Data</h4>
                  </div>
                )}
                <div
                  className="col-12"
                  style={{
                    textAlign: "center",
                    marginTop: "10%",
                  }}
                  hidden={this.state.empty}
                >
                  <h4>There's No Project In This Generation or Courseh</h4>
                </div>
              </div>
            </>
          ) : (
            <>
              {this.state.isLoading && <Loading height="60vh" />}
              <div className="row">
                {this.state.isLoading !== true ? (
                  this.props.dailyreport.map((datas, i) => (
                    <Link
                      to={{
                        pathname: `/project_division/new_assign_project/${datas.id}`,
                        // state: {
                        //   cID: this.state.course,
                        //   gID: this.state.generation,
                        // },
                      }}
                      className="col-lg-3 col-md-6 col-sm-12 mt-lg-2 mt-2 text-decoration-none"
                    >
                      <div className="bck ">
                        <div className="row">
                          <div className="col-lg-3 col-sm-3 col-3 c-1 text-white">
                            <b>{datas.name.charAt(0).toUpperCase()}</b>
                          </div>
                          <div
                            className="col-lg-9 col-sm-9 col-9 c-2 text-white text-truncate"
                            title={datas.name}
                          >
                            {datas.name}
                          </div>
                        </div>
                      </div>
                    </Link>
                  ))
                ) : (
                  <span></span>
                )}
              </div>
            </>
          )}
        </div>
      </>
    ) : (
      <Redirect to="/403"></Redirect>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    dailyreport: state.dailyReportReducer.ProjectFilter,
    course: state.courseReducer.course,
    generation: state.generationReducer.generation,
  };
};

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getProjectFilter,
      getCourse,
      getGeneration,
    },
    dispatch
  );
};
export default connect(mapStateToProps, mapDispatchToProps)(AssignProject);
