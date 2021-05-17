import axios from "axios";

// axios 객체 생성
export default axios.create({
<<<<<<< HEAD
  baseURL: "http://localhost:8080/api",
  // baseURL : "https://k4b107.p.ssafy.io:8080/api",
=======
  baseURL: "https://localhost:8080/api",
  //baseURL : "https://k4b107.p.ssafy.io:8080/api",
>>>>>>> 418d5a32d4e6a7be27b8945b7a6b96a7a43666a3
  headers: {
    "Content-type": "application/json",
  },
});
