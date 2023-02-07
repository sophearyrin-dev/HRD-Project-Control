import React from "react";
import error from "../../img/403.png";
import Particles from "react-particles-js";
import { Link } from "react-router-dom";
import "./page404.css";

function logOut(e) {
  e.preventDefault();
  localStorage.removeItem("user");
  if (typeof window !== "undefined") {
    window.location.href = "/Login";
  }
}
export default function loading() {
  return (
    <div style={{ height: "100vh" }}>
      <Particles
        id="particles"
        params={{
          particles: {
            color: {
              value: "#3c4b64",
            },
            number: {
              value: 180,
              density: {
                enable: false,
              },
            },
            size: {
              value: 3,
              random: true,
              anim: {
                speed: 4,
                size_min: 0.3,
              },
            },
            line_linked: {
              enable: false,
            },
            move: {
              random: true,
              speed: 1,
              direction: "top",
              out_mode: "out",
            },
          },
          interactivity: {
            events: {
              onhover: {
                enable: true,
                mode: "bubble",
              },
              onclick: {
                enable: true,
                mode: "repulse",
              },
            },
            modes: {
              bubble: {
                distance: 250,
                duration: 2,
                size: 0,
                opacity: 0,
              },
              repulse: {
                distance: 400,
                duration: 4,
              },
            },
          },
        }}
      />

      <>
        <div
          className="text-center d-flex align-items-center justify-content-center"
          style={{ background: "#ebedef", height: "100vh" }}
        >
          <div className="mx-auto">
            <img src={error} height="100%" width="100%" alt="404page" />
            <Link
              onClick={logOut}
              to="/"
              type="button"
              className="custom_btn btn-ms blue text-decoration-none"
            >
              <b>Back</b>
            </Link>
          </div>
        </div>
      </>
    </div>
  );
}
