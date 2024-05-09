document.addEventListener('DOMContentLoaded', function () {
  const currentUrl = window.location.pathname;

  const li1 = document.querySelector('body > div > ul > li:nth-child(3)');
  const li2 = document.querySelector('body > div > ul > li:nth-child(4)');
  const li3 = document.querySelector('body > div > ul > li:nth-child(5)');
  if (currentUrl === '/main_dashboard.mustache' || currentUrl === '/') {
    li1.classList.add('menu-chosen');
    li2.classList.remove('menu-chosen');
    li3.classList.remove('menu-chosen');
  } else if (currentUrl === '/map.mustache') {
    li1.classList.remove('menu-chosen');
    li2.classList.add('menu-chosen');
    li3.classList.remove('menu-chosen');
  } else if (currentUrl === '/favourites') {
    li1.classList.remove('menu-chosen');
    li2.classList.remove('menu-chosen');
    li3.classList.add('menu-chosen');
  }
});
