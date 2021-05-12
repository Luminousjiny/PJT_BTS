import axios from "axios";

// axios 객체 생성
export default axios.create({
  baseURL: "http://localhost:8080/api",
  // baseURL : "http://k4b107.p.ssafy.io/",
  headers: {
    "Content-type": "application/json",
  },
});
