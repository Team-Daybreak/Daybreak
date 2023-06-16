const login_btn = document.querySelector(".loginForm");
const dropdown_login_box = document.querySelector("#dropdown-login");

handleClickLoginBtn = () => {
  dropdown_login_box.classList.toggle("show");
}

window.onclick = function (event) {
  const cond1 = String(event.path[1].id) === 'dropdown-login';
  const cond2 = String(event.path[1].id) === 'login-form';
  const clickedInsideLoginForm = cond1 || cond2;
  if (clickedInsideLoginForm) {
    return;
  }
  if (!event.target.matches('.loginForm')) {
    if (dropdown_login_box.classList.contains('show')) {
      dropdown_login_box.classList.remove('show');
    }
  }
}

login_btn.addEventListener("click", handleClickLoginBtn);