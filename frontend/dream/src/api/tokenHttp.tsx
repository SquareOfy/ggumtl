import axios from "axios";
import jwt_decode, { JwtPayload } from "jwt-decode";
import dayjs from "dayjs";

// 토큰이 필요한 인증에 사용

// const baseURL = process.env.REACT_APP_SERVER_URL;
// const baseURL = "https://j9b301.p.ssafy.io/api";
const baseURL = "http://localhost:9090/api";

const tokenHttp = axios.create({
  baseURL,
  withCredentials:false, //임시, 후에 지워야함
  headers: {
    "Content-Type": "application/json",
  },
});

// 요청 인터셉터 설정 (요청 보내기 전에 수행되는 함수)
tokenHttp.interceptors.request.use(async (req) => {
  const accessToken = sessionStorage.getItem("accessToken");
  if (!accessToken) {
    console.log("token 이 존재하지 않습니다.");
    throw new Error("expire token");
  }

  const user = jwt_decode<JwtPayload>(accessToken);
  const isExpired = dayjs().diff(dayjs.unix(user.exp as number)) < 1;

  // access token 이 만료되지 않았다면 access-token 을 넣어 요청 실행
  if (isExpired) {
    req.headers["Authorization"] = `Bearer ${accessToken}`;
    return req;
  }

  // 만료되었다면 refresh-token으로 token 재발급
  console.log("api/tokenHttp.js : access token 만료");

  await axios
    .post(
      `${baseURL}/user/refresh-token`,
      {},
      {
        headers: {
          Authorization: sessionStorage.getItem("refreshToken"),
        },
      }
    )
    .then((response) => {
      if (response.data.message === "success") {
        sessionStorage.setItem("accessToken", response.data["accessToken"]);
        sessionStorage.setItem("refreshToken", response.data["refreshToken"]);
      } else {
        throw new Error("expire token");
      }
    })
    .catch(() => {
      throw new Error("expire token");
    });

  req.headers["Authorization"] = sessionStorage.getItem("accessToken");
  return req;
});

export default tokenHttp;
