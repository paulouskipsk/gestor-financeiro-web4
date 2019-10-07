<%@ tag language="java" pageEncoding="UTF-8"%>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
  <a class="navbar-brand" href="#">Gestor</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="home">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="despesa-novo">Receitas</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="despesas">Despesas</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Relatorios</a>
        <div class="dropdown-menu">
          <a class="dropdown-item" href="despesas">Despesas</a>
          <a class="dropdown-item" href="#">Receitas</a>
          <a class="dropdown-item" href="#">Orcamento</a>
        </div>
      </li>
    </ul>
  </div>
</nav>