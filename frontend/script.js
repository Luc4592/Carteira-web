document.getElementById('loginForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    const user = document.getElementById('username').value;
    const pass = document.getElementById('password').value;

    try {
        const response = await fetch('http://localhost:8080/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: user,
                password: pass
            })
        });

        if (response.ok) {
            document.getElementById('message').style.color = 'green';
            document.getElementById('message').innerText = 'Login realizado com sucesso!';
            // Tenta ler o JSON, mas ignora se der erro
            try { await response.json(); } catch (e) {}
            setTimeout(() => {
                window.location.href = "dashboard.html";
            }, 1000);
        } else {
            document.getElementById('message').style.color = 'red';
            document.getElementById('message').innerText = 'Usuário ou senha inválidos!';
        }
    } catch (error) {
        console.error('Erro na requisição:', error);
        document.getElementById('message').style.color = 'orange';
        document.getElementById('message').innerText = 'Erro de conexão com o servidor!';
    }
});

const tr = document.createElement('tr');
tr.style.position = "relative";
tr.innerHTML = `
  <td>
    <a href="empresa.html?ticker=${ticker}" style="color:#007bff;text-decoration:underline;cursor:pointer;">
      ${ticker}
    </a>
  </td>
  <td>${info.nome}</td>
  <td>${info.tipo}</td>
  <td>${valor !== "N/A" ? "R$ " + valor.toFixed(2) : "N/A"}</td>
`;

const btn = document.createElement('button');
btn.textContent = '-';
btn.title = 'Remover';
btn.className = 'remove-btn';
btn.style.cssText = `
  position: absolute;
  top: 50%;
  right: 8px;
  transform: translateY(-50%);
  background: #ff4444;
  color: #fff;
  border: none;
  border-radius: 50%;
  width: 22px;
  height: 22px;
  font-size: 14px;
  line-height: 18px;
  padding: 0;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
`;

btn.onclick = function() {
  tr.remove();
};
tr.appendChild(btn);
tbody.appendChild(tr);
