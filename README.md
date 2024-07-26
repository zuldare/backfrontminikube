# Despliegue del backend mediante minikube

En esta solución nos iremos acercando al resultado final esperado que será desplegar backend y frontend en local bajo minikube simulando un despliegue real en kubernetes.

En esta opción no vamos a usar un manifiesto, sino que vamos a directamente exponer puertos, servicios, etc.

## Pasos

1. Arrancar minikube

```bash
minikube start
```

2. Hacer que Docker trabaje dentro de minikube

```bash
eval $(minikube docker-env)
```

3. Construir la imagen docker

```bash
docker built -t backapp:v2 .
```

4. Crear el deployment hacendo que apunte al puerto 8080, con la imagen creada anteriormente

```bash
kubectl create deployment backapp --image=backapp:v2 --port=8080
```

5. Crear el servicio haciendolo de tipo NodePort

```bash
kubectl expose deployment backapp --type=NodePort
```

![alt text](/imgs/image2Full.png)

6. Obtener la url del servicio

```bash
minikube service backapp
```

![alt text](/imgs/image2Service.png)

7. Copiamos la url que nos devuelve el paso anterior y lo ponemos en frontapp
   ![alt text](imgs/image2frontchanged.png)

8. Lanzamos el comando para desplegar la aplicación front

```bash
npm run serve
```

![alt text](imgs/image2finalfront.png)
