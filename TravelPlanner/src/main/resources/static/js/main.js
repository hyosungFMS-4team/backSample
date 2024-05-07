const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');
console.log(signInButton, signUpButton, container, document)

signUpButton.addEventListener('click', () => {
  container.classList.add('right-panel-active');
});

signInButton.addEventListener('click', () => {
  container.classList.remove('right-panel-active');
});

const checkPassword = () => {
  let password = document.getElementById('password');
  let passwordChk = document.getElementById('passwordChk');
  if (passwordChk.value === password.value) {
    document.getElementById('label3').style.color = 'green';
    document.getElementById('label3').textContent = '비밀번호가 일치합니다.';
    document.querySelector('.submitBtn').disabled = false;
  } else {
    document.getElementById('label3').style.color = 'red';
    document.getElementById('label3').textContent = '비밀번호가 일치하지 않습니다.';
    document.querySelector('.submitBtn').disabled = true;
  }
};

document.addEventListener('DOMContentLoaded', function () {
  let idInput = document.getElementById('id');
  idInput.addEventListener('focusout', function () {
    let id = idInput.value;
  console.log(id)
    if (id === '' || id.length === 0) {
      document.getElementById('label1').style.color = 'red';
      document.getElementById('label1').textContent = '공백은 ID로 사용할 수 없습니다.';
      return false;
    }

    fetch('/signup/memberid-check', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: encodeURIComponent(id),
    })
      .then(response => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('Network response was not ok.');
        }
      })
      .then(result => {
        if (result === true) {
          document.getElementById('label1').style.color = 'black';
          document.getElementById('label1').textContent = '사용 가능한 ID 입니다.';
        } else {
          document.getElementById('label1').style.color = 'red';
          document.getElementById('label1').textContent = '사용 불가능한 ID 입니다.';
          idInput.value = '';
        }
      })
      .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
      });
  });
});
