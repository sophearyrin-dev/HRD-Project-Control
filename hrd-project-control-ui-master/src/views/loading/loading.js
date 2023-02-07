import React from "react";
import Loader from "react-loader-spinner";
export default function loading(props) {
  return (
    <>
      <div
        className="text-center d-flex align-items-center justify-content-center"
        style={{ background: "#ebedef", height: props.height }}
      >
        <div className="mx-auto">
          <Loader type="Oval" color="#2F3539" height={80} width={80} />
          Please Wait
        </div>
      </div>
    </>
  );
}
