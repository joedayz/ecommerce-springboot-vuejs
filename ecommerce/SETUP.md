# Configuración del Entorno de Desarrollo

## Configuración de Variables de Entorno

Este proyecto utiliza archivos de propiedades separados para manejar datos sensibles de manera segura.

### Pasos para configurar:

1. **Copiar el archivo de ejemplo:**
   ```bash
   cp src/main/resources/application-dev.properties.example src/main/resources/application-dev.properties
   ```

2. **Editar el archivo con tus datos reales:**
   - Abre `src/main/resources/application-dev.properties`
   - Reemplaza los valores de ejemplo con tus datos reales:
     - `your_username` → tu usuario de MySQL
     - `your_password` → tu contraseña de MySQL
     - `sk_test_your_stripe_secret_key_here` → tu clave secreta de Stripe

### Archivos importantes:

- `application.properties` - Configuración general (SÍ se sube a git)
- `application-dev.properties` - Datos sensibles de desarrollo (NO se sube a git)
- `application-dev.properties.example` - Ejemplo de configuración (SÍ se sube a git)

### Obtener claves de Stripe:

1. Ve a [Stripe Dashboard](https://dashboard.stripe.com/apikeys)
2. Copia tu "Secret key" (empieza con `sk_test_` para desarrollo)
3. Pégala en `application-dev.properties`

### Seguridad:

- ✅ `application-dev.properties` está en `.gitignore`
- ✅ Solo se suben archivos de ejemplo a git
- ✅ Las claves secretas nunca se exponen públicamente

### Para producción:

Crea un archivo `application-prod.properties` con las claves de producción de Stripe (empiezan con `sk_live_`).
