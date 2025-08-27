# Ecommerce - Configuración Simple

Configuración mínima para correr MySQL en contenedor y la aplicación Spring Boot localmente.

## 🚀 Inicio Rápido

### 1. Iniciar MySQL Container

```bash
# Iniciar MySQL
podman-compose up -d

# Verificar que esté corriendo
podman-compose ps
```

### 2. Correr la Aplicación Spring Boot Localmente

```bash
# Usando Maven
mvn spring-boot:run

# O usando el wrapper
./mvnw spring-boot:run
```

## 📊 Configuración de Base de Datos

- **Host:** localhost:3307
- **Database:** ecommerce
- **Username:** root
- **Password:** joedayz

## 🛠️ Comandos Útiles

```bash
# Ver logs de MySQL
podman-compose logs mysql

# Conectar a MySQL
podman exec -it ecommerce-mysql mysql -u root -p

# Parar MySQL
podman-compose down

# Parar y eliminar datos
podman-compose down -v
```

## 📝 Notas

- La aplicación Spring Boot corre en `http://localhost:8080`
- MySQL corre en `localhost:3307`
- Los datos de MySQL se persisten en un volumen
- La aplicación se conecta automáticamente a la base de datos
