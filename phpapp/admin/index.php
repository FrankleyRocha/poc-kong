<?php
echo "Hello, Admin!\n";

$username = $_SERVER['HTTP_X_CONSUMER_USERNAME'] ?? 'desconhecido';
echo "Usuário: " . htmlspecialchars($username) . "\n";

$groups = $_SERVER['HTTP_X_CONSUMER_GROUPS'] ?? 'nenhum grupo';
echo "Grupos do consumidor: " . htmlspecialchars($groups) . "\n";