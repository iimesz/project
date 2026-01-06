(function(){
  const form = document.getElementById('regForm');
  const msg = document.getElementById('message');

  function showMessage(text, isError) {
    msg.textContent = text;
    msg.className = isError ? 'error' : 'success';
  }

  form.addEventListener('submit', async (e) => {
    e.preventDefault();
    msg.textContent = '';

    const formData = new FormData(form);
    const payload = {
      email: formData.get('email'),
      firstName: formData.get('firstName'),
      lastName: formData.get('lastName'),
      birthDate: formData.get('birthDate'),
      password: formData.get('password'),
      country: formData.get('country'),
      city: formData.get('city'),
      phoneNumber: formData.get('phoneNumber') || null
    };

    try {
      const res = await fetch('/api/users/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });

      if (res.status === 201) {
        const data = await res.json();
        showMessage('Registration successful. ID: ' + data.id, false);
        form.reset();
      } else if (res.status === 409) {
        const text = await res.text();
        showMessage(text || 'Conflict: email exists', true);
      } else if (res.status === 400) {
        const text = await res.text();
        showMessage('Validation failed: ' + text, true);
      } else {
        const text = await res.text();
        showMessage('Server error: ' + text, true);
      }
    } catch (err) {
      showMessage('Network error: ' + err.message, true);
    }
  });
})();
