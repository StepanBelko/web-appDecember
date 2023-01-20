<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
  ul {
    display: inline-grid;
    grid-auto-flow: row;
    grid-gap: 24px;
    justify-items: center;
    margin: auto;

  }

  @media (min-width: 500px) {
    ul {
      grid-auto-flow: column;
    }
  }

  .myFooterClass a {
    color: white;
    text-decoration: none;
    box-shadow: inset 0 -1px 0 hsla(0, 0%, 100%, 0.4);
  }

  .myFooterClass a:hover {
    box-shadow: inset 0 -1.2em 0 hsla(0, 0%, 100%, 0.4);
  }

  li:last-child {
    grid-column: 1 / 2;
    grid-row: 1 / 2;
  }

  li:hover ~ li p {
    animation: wave-animation 0.3s infinite;
  }

  /* below is just for demo styling */

  .myFooterClass {
    display: flex;
    height: 60px;
    width: 100%;
    background-color: #282830;
    line-height: 1.3;
    font-family: Menlo, monospace;
    border-radius: 0 0 10px 10px;
  }

  @keyframes wave-animation {
    0%,
    100% {
      transform: rotate(0deg);
    }
    25% {
      transform: rotate(20deg);
    }
    75% {
      transform: rotate(-15deg);
    }
  }


</style>
<div class="myFooterClass">
  <ul>
    <li><a href="https://twitter.com/home?lang=ru">Twitter</a></li>
    <li><a href="mailto:Stpn.belko@gmail.com">Email</a></li>
    <li><a href="https://github.com/StepanBelko">Github</a></li>
  </ul>
</div>