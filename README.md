# Despliegue del backend mediante minikube

En esta solución nos iremos acercando al resultado final esperado que será desplegar backend y frontend en local bajo minikube simulando un despliegue real en kubernetes.

En esta opción vamos a utilizar un manifiesto para el frontend mediante un ingress

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
docker built -t frontapp:dragonball .
```

4. Crear el manifiesto creando tanto la parte del deployment como la parte del servicio

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: frontapp-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: frontapp
  template:
    metadata:
      labels:
        app: frontapp
    spec:
      containers:
        - name: frontapp
          image: frontapp:dragonball
          imagePullPolicy: IfNotPresent
          ports:
            - containerPort: 80

---
apiVersion: v1
kind: Service
metadata:
  name: frontapp-service
spec:
  selector:
    app: frontapp
  ports:
    - protocol: TCP
      port: 80
      targetPort: 80
  type: LoadBalancer

---
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: frontapp-ingress
  annotations:
    nginx.ingress.kubernetes.io/rewrite-target: /
spec:
  rules:
    - host: frontapp-dragonball.local
      http:
        paths:
          - path: /
            pathType: Prefix
            backend:
              service:
                name: frontapp-service
                port:
                  number: 80
```

5. Aplicamos el manifiesto

```bash
kubectl apply -f deployment.yaml
```

### OJO PREGUNTAR XQ SIN EL TUNNEL NO FUNCIONA Y XQ EXTERNAL-IP ES 127.0.0.1 Y NO minikube ip

6. Abrir tunel en minikube

```bash
minikube tunnel
```

```bash
kubectl get services
```

y la salida sería similar a esta

```text
NAME                      TYPE           CLUSTER-IP      EXTERNAL-IP     PORT(S)        AGE
frontapp-service          LoadBalancer   10.96.198.181   127.0.0.1       80:31500/TCP   5m
```

y al entrar en la ruta indicada se podrá ver el servicio

![alt text](image.png)
