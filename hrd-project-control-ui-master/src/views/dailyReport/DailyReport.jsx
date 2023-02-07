import React, { PureComponent } from "react";

import { Link } from "react-router-dom";
import { bindActionCreators } from "redux";
import { getCourse } from "../../redux/actions/Course/courseAction";
import { getGeneration } from "../../redux/actions/Generation/generationAction";
import { getDailyReport } from "../../redux/actions/DailyReport/dailyreportAction";
import { connect } from "react-redux";
import Loading from "./../loading/loading";
import "../dailyReport/dailyReport.css";
import localStorage from "../../redux/actions/Login/securityUtils/localStorages";
import { getUserProject } from "../../redux/actions/Dashboard/dashboardAction";

class ProjectDivision extends PureComponent {
  constructor(props) {
    super();
    this.state = {
      generation: 0,
      course: 0,
      generationId: "",
      courseId: "",
      isLoading: false,
      user: localStorage(),
      isNull: true,
      isLoadingUser: true,
      empty: true,
    };
  }

  showSuperMentor() {
    this.props.getGeneration();
    this.props.getCourse();
  }

  componentDidMount() {
    this.state.user.role === "SUPER_MENTOR" || this.state.user.role === "MENTOR"
      ? this.showSuperMentor()
      : this.props
          .getUserProject(this.state.user.id)
          .then(() => this.setState({ isLoading: true, isLoadingUser: false }));
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
      .getDailyReport(this.state.course, this.state.generation)
      .then(() =>
        this.setState({
          isLoading: false,
          empty: false,
        })
      );
  };

  cutValue(value) {
    let name = String(value);
    return name.charAt(0);
  }

  render() {
    return (
      <>
        <h3>
          {" "}
          <i height={25} className="fas fa-clipboard-list    "></i> Daily Report
        </h3>
        <div>
          {this.state.user.role === "SUPER_MENTOR" ||
          this.state.user.role === "MENTOR" ? (
            <>
              <div>
                <div>
                  {" "}
                  <div className="form-row float-right">
                    <div className="col-auto ">
                      <label>Generation</label>
                      <select
                        className="custom-select"
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
                        className="custom-select"
                        value={this.state.course}
                        onChange={this.handleChange}
                        name="course"
                      >
                        <option defaultValue={this.state.course}>
                          Choose...
                        </option>
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
                        className="btn btn-primary"
                      >
                        Filter
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </>
          ) : (
            <>
              <span></span>
            </>
          )}
          <div className="clearfix"> </div>

          <>
            {this.state.user.role === "SUPER_MENTOR" ||
            this.state.user.role === "MENTOR" ? (
              <>
                {this.state.isLoading && <Loading height="60vh" />}
                {this.props.dailyreport.length < 1 ? (
                  <>
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
                    </div>
                    <div
                      className="col-12"
                      style={{
                        textAlign: "center",
                        marginTop: "10%",
                      }}
                      hidden={this.state.empty}
                    >
                      <h4>There's No Project In This Generation or Course</h4>
                    </div>
                  </>
                ) : (
                  <div className="row mt-2">
                    {this.state.isLoading !== true ? (
                      this.props.dailyreport.map((datas, i) => (
                        <Link
                          to={`/viewDailyReport/${datas.id}`}
                          class="col-lg-3 col-md-6 col-sm-12 mt-lg-2 mt-2 text-decoration-none"
                        >
                          <div class="bck ">
                            <div class="row">
                              <div class="col-lg-3 col-sm-3 col-3 c-1 text-white">
                                <b>{datas.name.charAt(0).toUpperCase()}</b>
                              </div>
                              <div
                                class="col-lg-9 col-sm-9 col-9 c-2 text-white text-truncate"
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
                )}
              </>
            ) : (
              <>
                {this.state.isLoadingUser ? (
                  <Loading height="75vh" />
                ) : (
                  <div className="row mt-2">
                    {this.state.isLoading ? (
                      this.props.userProject.map((data, i) =>
                        this.props.checkProject
                          ? data.courseId === 2 && (
                              <Link
                                to={`/viewDailyReport/${data.projectId}`}
                                class="col-lg-3 col-md-6 col-sm-12 mt-lg-2 mt-2 text-decoration-none"
                              >
                                <div class="bck ">
                                  <div class="row">
                                    <div class="col-lg-3 col-sm-3 col-3 c-1 text-white">
                                      <b>{this.cutValue(data.name)}</b>
                                    </div>
                                    <div
                                      class="col-lg-9 col-sm-9 col-9 c-2 text-white  text-truncate"
                                      title={data.name}
                                    >
                                      {data.name}
                                    </div>
                                  </div>
                                </div>
                              </Link>
                            )
                          : data.courseId === 1 && (
                              <Link
                                to={`/viewDailyReport/${data.projectId}`}
                                class="col-lg-3 col-md-6 col-sm-12 mt-lg-2 mt-2 text-decoration-none"
                              >
                                <div class="bck ">
                                  <div class="row">
                                    <div class="col-lg-3 col-sm-3 col-3 c-1 text-white">
                                      <b>{this.cutValue(data.name)}</b>
                                    </div>
                                    <div
                                      class="col-lg-9 col-sm-9 col-9 c-2 text-white  text-truncate"
                                      title={data.name}
                                    >
                                      {data.name}
                                    </div>
                                  </div>
                                </div>
                              </Link>
                            )
                      )
                    ) : (
                      <span></span>
                    )}
                  </div>
                )}
              </>
            )}
          </>
        </div>
      </>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    dailyreport: state.dailyReportReducer.dailyreport,
    course: state.courseReducer.course,
    generation: state.generationReducer.generation,
    userProject: state.dashboardReducer.userProject,
    checkProject: state.dashboardReducer.check,
  };
};

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getDailyReport,
      getCourse,
      getGeneration,
      getUserProject,
    },
    dispatch
  );
};
export default connect(mapStateToProps, mapDispatchToProps)(ProjectDivision);
