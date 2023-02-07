var CryptoJS = require("crypto-js");

export default function authHeader() {
  const users = JSON.parse(localStorage.getItem("user"));
  if (users && users.jwtToken) {
    let decrypt = CryptoJS.AES.decrypt(users.jwtToken, "hrd-project-control");
    let getDecrypt = decrypt.toString(CryptoJS.enc.Utf8);

    return { authorization: `Bearer ${getDecrypt}` };
  } else {
    return {};
  }
}
