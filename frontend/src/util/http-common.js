import axios from "axios";

// axios 객체 생성
export default axios.create({
  baseURL: "https://localhost:8080/api",
<<<<<<< HEAD
  // baseURL : "https://k4b107.p.ssafy.io:8080/api",
=======
  //baseURL : "https://k4b107.p.ssafy.io:8080/api",
>>>>>>> 861d8ecf3112f73fbe34e2b07ee61f58150bd020
  headers: {
    "Content-type": "application/json",
  },
});
