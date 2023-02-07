import React, { PureComponent } from "react";
import "./dailyReport.css";
import { bindActionCreators } from "redux";
import Moment from "moment";
import "./dailyReport.css";
import { Modal, Button, Table, Tooltip, OverlayTrigger } from "react-bootstrap";
import { connect } from "react-redux";
import { MDBDataTable } from "mdbreact";
import Loader from "react-loader-spinner";
import {
  getviewDailyReport,
  getListReport,
} from "../../redux/actions/DailyReport/dailyreportAction";
import Loading from "./../loading/loading";

class viewDailyReport extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      show_id: this.props.match.params.id,
      count: 1,
      expandedRows: null,
      isLoading: true,
      isLoadingModal: true,
      isNull: false,
      show: false,
      counts: 0,
    };
  }
  componentDidMount() {
    this.props.getviewDailyReport(this.state.show_id).then(() =>
      this.setState({
        isLoading: false,
        isNull: true,
      })
    );
  }
  handleClose = () => {
    this.setState({ show: false });
    this.setState({ expandedRows: null });
    this.setState({
      isLoadingModal: true,
    });
  };
  handleRowClick = (rowId) => {
    this.setState({
      expandedRows: rowId,
      show: true,
    });
    this.props.getListReport(rowId, this.state.show_id).then(() =>
      this.setState({
        isLoadingModal: false,
      })
    );

    if (this.state.isLoading === true) {
      document.getElementById("MyElement").className = "hide-table";
    }
  };
  renderTooltip = (props) => (
    <Tooltip id="button-tooltip" {...props}>
      Detail Informations
    </Tooltip>
  );
  showTable = () => {
    let data = this.props.viewdailyreport.map((datas, i) => {
      return {
        id: i + 1,
        nameCurrentDate: datas.currentDate,
        nameDeveloperName: datas.developerName,
        nameMainTask: datas.mainTask,
        nameSubTask: datas.subTask,
        nameMessage: datas.message,
        view: (
          <OverlayTrigger
            placement="top"
            delay={{ show: 250, hide: 400 }}
            overlay={this.renderTooltip}
            className="text-center"
          >
            <button
              type="button"
              className="mx-1 btn btn-sm btn-success"
              onClick={() => {
                this.handleRowClick(datas.currentDate);
              }}
              key={"row-data" + datas.currentDate}
            >
              <i className="fas fa-eye    "></i>
            </button>
          </OverlayTrigger>
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
          label: "Current Date",
          field: "nameCurrentDate",
          sort: "asc",
        },

        {
          label: "Action",
          field: "view",
          sort: "asc",
        },
      ],
      rows: this.showTable(),
    };
    const showModalTable = (
      <>
        <div
          style={{
            width: "100%",
            height: "100",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            backgroundColor: "white",
          }}
        >
          {" "}
          {this.state.isLoadingModal && (
            <Loader type="Bars" color="#2f353a" height={50} width={50} />
          )}
        </div>
        <Table
          striped
          bordered
          hover
          className={this.state.isLoadingModal === true ? "hide-table" : ""}
        >
          <thead>
            <tr>
              <th>#</th>
              <th>Current Date</th>
              <th>Developer Name</th>
              <th>Main Task</th>
              <th>Sub Task</th>
              <th>DeadLine</th>
              <th>Percentage</th>
              <th>Message</th>
            </tr>
          </thead>
          <tbody>
            {this.props.listreport.map((data, index) => (
              <tr key={"row-expanded-" + data.index}>
                <td>{index}</td>
                <td>{Moment(data.currentDate).format("YYYY-MM-DD")}</td>
                <td>{data.developerName}</td>
                <td>{data.mainTask}</td>
                <td>{data.subTask}</td>
                <td>{data.deadline}</td>
                <td>{data.progressPercentage}</td>
                <td className="text-truncate" title={data.message}>
                  {data.message}
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </>
    );
    return (
      <div>
        <Modal
          dialogClassName="my-modal"
          show={this.state.show}
          onHide={this.handleClose}
          animation={false}
        >
          <Modal.Header closeButton>
            <Modal.Title>
              <h3>
                {" "}
                <i height={25} className="fas fa-clipboard-list    "></i> View
                Daily Report
              </h3>
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>{showModalTable}</Modal.Body>
          <Modal.Footer>
            <Button variant="danger" onClick={this.handleClose}>
              Close
            </Button>
          </Modal.Footer>
        </Modal>
        <h3>
          {" "}
          <i height={25} className="fas fa-clipboard-list    "></i> View Daily
          Report
        </h3>
        <br />
        {this.state.isLoading && <Loading height="75vh" />}

        {this.props.viewdailyreport.length < 1 ? (
          <>
            {this.state.isNull && (
              <div
                style={{
                  left: "45%",
                  top: "50%",
                  position: "absolute",
                }}
              >
                <h2>There's No Report For This Project's</h2>
              </div>
            )}
          </>
        ) : (
          <MDBDataTable
            striped
            bordered
            hover
            data={data}
            btn
            className={this.state.isLoading === true ? "hide-table" : ""}
          />
        )}
      </div>
    );
  }
}
const mapStateToProps = (state) => {
  return {
    viewdailyreport: state.dailyReportReducer.viewdailyreport,
    listreport: state.dailyReportReducer.listreport,
  };
};
const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      getListReport,
      getviewDailyReport,
    },
    dispatch
  );
};
export default connect(mapStateToProps, mapDispatchToProps)(viewDailyReport);
