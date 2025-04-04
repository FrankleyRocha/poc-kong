<?php
echo "Hello, Admin!";

$username = $_SERVER['HTTP_X_CONSUMER_USERNAME'] ?? 'desconhecido';
echo "Usuário: " . htmlspecialchars($username);

$groups = $_SERVER['HTTP_X_CONSUMER_GROUPS'] ?? 'nenhum grupo';
echo "Grupos do consumidor: " . htmlspecialchars($groups);