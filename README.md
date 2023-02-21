Секреты:
Секреты  - base64 encoded. Секреты вывели в отдельный ресурс, чтобы раздать разные права
kubectl get secrets -A

kubectl create secret generic k8s-example-simple --from-literal=app.custom.secret=secretValue -n demo
kubectl describe secret k8s-example-simple -n demo
kubectl get secret/k8s-example-simple -o json -n demo

kubectl port-forward deploy/k8s-example-simple -n demo 8080:8081


NodePort
![img.png](img.png)

Ingress
![img_1.png](img_1.png)



helm install nginx ingress-nginx/ingress-nginx -f nginx/values-local.yaml

Assigning Pods to Nodes
https://kubernetes.io/docs/concepts/scheduling-eviction/assign-pod-node/


General info:
1. Начало работы (проверка существующего контекста и выбор нужного)

kubectl config get-contexts
kubectl config use-context docker-desktop
kubectl config current-context
kubectl config get-users


kubectl api-resources                --- ресурсы k8s (NAME   SHORTNAMES   APIVERSION    NAMESPACED   KIND)


2. Локальный конфиг K8S
   C:\Users\userName\.kube


apiVersion: v1
clusters:
- cluster:
  certificate-authority-data: LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUMvakNDQWVhZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFWTVJNd0VRWURWUVFERXdwcmRXSmwKY201bGRHVnpNQjRYRFRJek1ESXdPVEE1TWpFd00xb1hEVE16TURJd05qQTVNakV3TTFvd0ZURVRNQkVHQTFVRQpBeE1LYTNWaVpYSnVaWFJsY3pDQ0FTSXdEUVlKS29aSWh2Y05BUUVCQlFBRGdnRVBBRENDQVFvQ2dnRUJBTU5VCmNvalNOaVk3ZUJEVmQrQ0ZycE5jMEtqckN5TUNzT2RZRXNSSmlaMU9lbmo3a1VRNkszc3liRmcwRkt6ZmU0WHMKK0pWcnFjS050MmtjL1JMUjRkZ0o1TkJHanlCRkJ5TTk0Rlc5KzNEYUFLRGM4U21pUjNpczF1RkhPZFVyOEJySgpMWHpSL2RYSlZ6czJQcGZMcVB0YUVpWWpzV0JYUTVkLzhKbGc5bTJ0c1JpMnhNb1hQcXg3a0pjRCtnRmdVdktyCjAzeWExZVRMQlVCVDVOZ3VzZW9yZzNpc3B3Q0d6NkQwcVg3cHhIK1A5VVE5UUd4MERuWlpua2ZEUFlENFlRS0MKQjBVZzFLYnc5QWdKVWMySUsvWjhBUjJ5TEwwZlN3Q0tvb2RFa3Vkb0JJaWovTFVqZlFlL2FLeGhZL2VSZHl3SwppaitnSEw0WXJVQTM5YlhUcmtVQ0F3RUFBYU5aTUZjd0RnWURWUjBQQVFIL0JBUURBZ0trTUE4R0ExVWRFd0VCCi93UUZNQU1CQWY4d0hRWURWUjBPQkJZRUZMcFlxSEt6d29mUGNvdFZ4T1QrdkZrOW9Ic2VNQlVHQTFVZEVRUU8KTUF5Q0NtdDFZbVZ5Ym1WMFpYTXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRWJTOHJvVS9kMHp5SHYrMk9XUQpaYzlRWVMwd2F5M3RFZU5TV01peXFBZklDY0dTRnhzbnYvTTlEM3p3MHc1ZjFnV01mNDdmK0pBbUZoSHRpZ0c2ClJlaVpZQzlOSkVZNS9BZ0F5RjNFWUsva1VVVTNrSlVia2dVSHowQkhIZlZ2SUFxQWxBcjByU0VyZ0luVlhhOE4KdGh4bFVPV09TdXZqVHpoSS81eWxlaVlxcndhUmdIakZ4THVTQUQzN1IrMEFlc201NEIvbTNuSjN6dExoOU1FMAo1TFZOb1F3TTJ4ZUxEVnJEL1c2YzhKRzFsUHhRRWRyL05Od3NGS1VNVzAyMGZJWERTTVViUE1HRGFJT1FMSGRYCmFLODZsOERFVS8zMUlSRkFUZlNEZ3dORTZzTmpyalJRdlZUWVY4dVZVK0VTTEFFMzBUd1pPb3BFdVR0YnA0akgKS1BrPQotLS0tLUVORCBDRVJUSUZJQ0FURS0tLS0tCg==   ----------------- сертификат аунтификации в кластере
  server: https://kubernetes.docker.internal:6443                                                                          ----------------- где кластер (его апи)
  name: docker-desktop                                                                                                       ----------------- имя кластера
  contexts:
- context:
  cluster: docker-desktop                    ----------- имя кластера   
  user: docker-desktop                       ----------- пользоватьель под котоым заходим в кластер
  name: docker-desktop                         ----------- имя контекста
  current-context: docker-desktop                ----------- текущий контекст
  kind: Config
  preferences: {}
  users:                                         ---------- настройки пользователя (сертификат и ключ)
- name: docker-desktop                         ---------- настройки для пользователя с именем docker-desktop
  user:
  client-certificate-data: LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSURRakNDQWlxZ0F3SUJBZ0lJRUQ4cG5naUN1Yk13RFFZSktvWklodmNOQVFFTEJRQXdGVEVUTUJFR0ExVUUKQXhNS2EzVmlaWEp1WlhSbGN6QWVGdzB5TXpBeU1Ea3dPVEl4TUROYUZ3MHlOREF5TURrd09USXhNRFJhTURZeApGekFWQmdOVkJBb1REbk41YzNSbGJUcHRZWE4wWlhKek1Sc3dHUVlEVlFRREV4SmtiMk5yWlhJdFptOXlMV1JsCmMydDBiM0F3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRREJ1SitESUpnSGFSbVcKbTl2ZURtaTVCMHExMVRjdHhOaTg0M3c0MmxoT2E5cDh4aDFZTEpCTy9KaHZxd0xrSE9hV2NsdStsSjE1bDBvQgpPNDYySjM5alZYeUVFWGtEL2wwMGNsTEJYRGUvMEp1MUVWZnFwTENrRGlrQ1pqenRxc25BVEoxSHU4aVR4TnM2CnQwVFhLcWRNeTF0K0I1Q0NYNW50NUFmc2t5dW5EbFAxcEhmMTZoNW5VOWpQMm5sZys0ZWdRVnRxT1hzNGRLZ1EKMkhwdVFOa21qdzVYTDMrdWQ2VGNWOGVXZ0NMbElHZ2pLKysyTENWSVlvWFhreFNNNDl6NlpndDlwRTY5WXZHSQpDK1N5dXNMRWNqMVFkSUNIaXR4L0d2d05jVHVYYlp1cGJiQTNIN3FTdnErWEE1NEFZVjVza3dBREZoeitzcCtjCmFSV1lqOVFQQWdNQkFBR2pkVEJ6TUE0R0ExVWREd0VCL3dRRUF3SUZvREFUQmdOVkhTVUVEREFLQmdnckJnRUYKQlFjREFqQU1CZ05WSFJNQkFmOEVBakFBTUI4R0ExVWRJd1FZTUJhQUZMcFlxSEt6d29mUGNvdFZ4T1QrdkZrOQpvSHNlTUIwR0ExVWRFUVFXTUJTQ0VtUnZZMnRsY2kxbWIzSXRaR1Z6YTNSdmNEQU5CZ2txaGtpRzl3MEJBUXNGCkFBT0NBUUVBUCszcDBKK3d1MEplQ09xMFZSckpZeHBVNUdKMFRNdis5S21rNmV3TGlzYU8xLzVOS3E4R2hDdEMKb3Rub2FIeXMxZHhiaEJtQUd0VlE1Rzk1T2o5WUdsTFhIUGhMWjRCV1lrWUgrRHVYRkR2TXE0MlBBNGZOTnZMdApncUV1VjZZblRva2RZay9xdXVodVRPYWNLTTBVSERHVkRUNlE3d0VaWXVYcjRnZ2E1L290TEJDeWkyL09iaitxCm10RmRPOE1TRWQxWTFYZ2JkY01xTWRUS0IvemlYN0VzU2wrcU9BN3hmRmNwMklReVdpdU1GSFZ3dURkRjBjYkIKTURzdjQzZDBsMWQyRTVobjNqMjBmZEJnMytqemxLNzhPcEpXL2QwTTdSV2ZMdkRjNW5hd1dpTlZoaVFqZnR2eApXNEVZcGlJYmlSb0h6UmRYM1lhcStxb2F1bkVlSlE9PQotLS0tLUVORCBDRVJUSUZJQ0FURS0tLS0tCg==
  client-key-data: LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFcFFJQkFBS0NBUUVBd2JpZmd5Q1lCMmtabHB2YjNnNW91UWRLdGRVM0xjVFl2T044T05wWVRtdmFmTVlkCldDeVFUdnlZYjZzQzVCem1sbkpidnBTZGVaZEtBVHVPdGlkL1kxVjhoQkY1QS81ZE5ISlN3VnczdjlDYnRSRlgKNnFTd3BBNHBBbVk4N2FySndFeWRSN3ZJazhUYk9yZEUxeXFuVE10YmZnZVFnbCtaN2VRSDdKTXJwdzVUOWFSMwo5ZW9lWjFQWXo5cDVZUHVIb0VGYmFqbDdPSFNvRU5oNmJrRFpKbzhPVnk5L3JuZWszRmZIbG9BaTVTQm9JeXZ2CnRpd2xTR0tGMTVNVWpPUGMrbVlMZmFST3ZXTHhpQXZrc3JyQ3hISTlVSFNBaDRyY2Z4cjhEWEU3bDIyYnFXMncKTngrNmtyNnZsd09lQUdGZWJKTUFBeFljL3JLZm5Ha1ZtSS9VRHdJREFRQUJBb0lCQVFDelg0czd0Sk53MFMxagpXbC9rcGZ6cnpzZXJ4SWg2TmJubWRORlNKMEw3SWp6RnFnQUQ1QWVmUzU2dVQyKzIzdGZZV0gyQTQwVkpELy96ClVqMWJod2thWms2TVQwMTVtQzZmWUhnbUdJaDZjT09zYmltMDE5ZkRvR1R5bHM0MTF4MUdsT3BSUUp5Qnp4YWIKc0pUcWt0RWtkMnY1TDNIc2RpOG1xaTE0TGZHT2xVWE9TMXMrS3lSNUNDS2ZEb0pCNXduaU50MG42ZlI3K0RpdApCdEVWaC9UNThEcWpqUGNVMkJWL0VzVERWQTk2R2xUdG0zMjhwcVBDYmdEVWtMNWlYZEVwR2J2WERBdnlyUmhJCm44ZnBoem8rYTh5cmZBSE9mNmtaZk0zbXp0b1ZnWFBPcm13N3BMZk5LZUFVUHhhN1VSY1E1U1RlalJaZjNSSFQKU0M1V3JiQnBBb0dCQU5PQTNWYXRRNjVZSk12eEczZzVHeVRSZFR1V0owUjgvbHVVMWloV3ZWV1lUcnl5WFZNQQpQR0V3WS9EOHRQZWJ2aE55M3RkNmRrWUVFUm1KUStjS044TjZQMDR0OHBxN09JMWxFVGJ3YkdURUxtRXRSeEJtCndVOEJyWjlqdzRSVmJNVXRQUStPWTYyRGFwM00ydENyVU5tSzVDZVNNUUZVREVRQ2Jrb04wZU90QW9HQkFPcDYKQzlkTnJWbHhRcTl3WUp4cjFtNU5jMWg2TURDWEdsUmdYdUs5UUY0b1oyTzY1eTVEYldqeFdBY09RaXgwWERqYQp0SWU0QUxXTFQzYnE2RHdRZGNZZWt4UXBWYUdhaXZnQVZkNzBuZmxXVkZkK1hGS2lKbXRlY2lUdWt3Vk9NblhlCmdTWXlJS252Vk5MWHdFc3ZkZFVaWXBUeHFCY0VrcFVHTVN6TnE2NHJBb0dBWkdCeEsxUzVuVUdKM1J4RmZDNGwKK3JoSm1nanVBK3dLVDI1NG9OWmhyd0RFeFhzbEhXSXNSYmZRaHF0NE8xYzN3MFV1Q3plOGNBZzRFVlY2MjJpWApCSGU5R1o1bTJCWm9oWHBrekIwWVpFNVM4SjJyYjFaRkFmajY3SDFOYWoyY2pJdW13ZjVDd2pOdll3cTVBblY3CnZuTFRTeEJ2bjREQy9YZmwzZEZuUGxVQ2dZRUF1UjhPaGpmL3F1UXZBQkE3Vm9RVlVoUzRBcHpGdXpjeFJIaXAKc1Y0MW1hRlFyMzU2MHgzTkhkT2Q2a1FwbnF0bnZFTi8wTDlIWUEzUytWSlJkRXFTa1k3R0ZGN21DcWJYSFNJMgpxY1o3aXNYTDVTQVFoL1VaWEIrL0FzV1JNZGtoSWNwQU1RWFdvQlFpNWl3TTE4OXF1b3BHT00zcUpmSW9RSndZCjZ3U2VqTzBDZ1lFQXlZWVNSWlQ2MVVGMkx3cEl5SmV2K2xtNm9QYVNBZ29qUmM4SW9wMityNzNJRjJyT1hucDQKRllNcCt4ZkJSa2FZbHVXcmNPSVd2Qm9UMUJqS1FWOHA3djJXVHRlQnhaNDlGa08xYTNacllGRzRhQmh0SWI0ZQpyeXdwa3lScGNqRjMwYmZ4dE5adzVCWTdyOVNlN0ppaHVKSVBoZjNSM1c3R0gzaVZWNDFBSktrPQotLS0tLUVORCBSU0EgUFJJVkFURSBLRVktLS0tLQo=


3. Работа с нейспейсом

kubectl get namespaces

4. Работа с кластером
   kubectl get pods
   kubectl get deploy
   kubectl get ns (kubectl get namespaces)

kubectl get deploy -A (получить из всех namespaces)
kubectl get deploy -n kube-system (деплойменты из конкретного namespace)
kubectl get deploy -A --show-labels (выбрать и показать лейблы)
kubectl get deploy -A -l k8s-app=kube-dns (поиск по леблам во всех namespace текущего контекста)


kubectl get pods -A -L app (отобразить дополнительно лейблы)
kubectl get pod -A -o wide (показать расширенную информацию по реурсу)

kubectl get po -o custom-columns=CONTAINER:.spec.containers[0].name,IMAGE:.spec.containers[0].image (выбрать кастомные колонки)
kubectl get -A po -o custom-columns=CONTAINER:.spec.containers[0].name,IMAGE:.spec.containers[0].image (выбрать кастомные колонки из всех ns)

kubectl get pod -A -l k8s-app=kube-dns -o json (показать информацию по реурсу в формате json, так как это лежит в БД)

kubectl get namespaces -w (watch - ждать изменений)
kubectl get namespaces --watch-only (отоброзиться только то, что изменилось (добавилось))


kubectl describe deploy -A -l k8s-app=kube-dns (подробная информация по ресурсу вместе с событиями)

kubectl create namespace demo
kubectl create deployment kuard --image=gcr.io/kuar-demo/kuard-amd64:1000 -n demo
kubectl describe pod/kuard-96b54dccb-dmltr -n demo
kubectl delete ns demo

kubectl create -f k8s/pod.yaml


kubectl rollout history deploy/umico-special-offers-manager -n demo

kubectl rollout status deploy/kuard -n demo --watch
kubectl rollout restart deploy/kuard -n demo (рестарт деплоймента без downtime)
kubectl get deploy/kuard -n demo --watch

5. Вывести под из деплоймента
   kubectl label pod/kuard-67f45b58-bz8b5 app- -n demo (убрать лейбл)
   kubectl get all -n demo --show-labels
   kubectl label pod/kuard-67f45b58-bz8b5 app=kuard -n demo (возвратит лейбл и этот под вернется, а новый уберется)
   kubectl get all -n demo --show-labels


kubectl describe pod/kuard-74b8c7b68b-tgn54 -n demo
kubectl port-forward deploy/kuard -n demo 8080:8080

kubectl describe  pod/kuard-74b8c7b68b-5w6gw -n demo



Передача конфигурации контейнеру:

1) Через env переменные
   env:                          #удобно, когда 1) их мало параметров 2) они не секретны 3) они стабильны (потому, что это деплоймент, нельзя нарушать его синглреспонсибилити)
   - name: DB_HOST
   value: 10.1.1.75


2) При наличии глобалынх енв переменных
   env:
   - name: DB_PORT_CONFIG   #pri nalichii globalnix config map
   valueFrom:
   configMapKeyRef:  #приемлемо для глобальных конфигов
   key: db.port
   name: kuard-with-config1

3) Когда конфигурация файлом

   spec:
   volumes:
   - name: config
   configMap:
   name: kuard-with-config-file
#            items:
#              - key: application.yaml
#                path: mnt/application-admin.yaml


      containers:
        - name: kuard-with-config-file
          image: gcr.io/kuar-demo/kuard-amd64:2
          imagePullPolicy: IfNotPresent
          ports:
            - containerPort: 8080
              name: http
          volumeMounts:
            - mountPath: /mnt/my_key
              name: config
              readOnly: true

4) Все ключи карты станут env переменными, плохо для файла потому, что название файла (например application.yaml) станет его ключем а содержимое значением
   envFrom:                                  
   - configMapRef:
   name: kuard-with-config-env-from



Секркреты:
Секреты  - base64 encoded. Секреты вывели в отдельный ресурс, чтобы раздать разные права
kubectl get secrets -A

kubectl create secret generic k8s-example-simple --from-literal=app.custom.secret=secretValue -n demo
kubectl describe secret k8s-example-simple -n demo
kubectl get secret/k8s-example-simple -o json -n demo

kubectl port-forward deploy/k8s-example-simple-communication -n demo 8082:8081


Трафик:
Отвечает ресурс: Service
kubectl expose deployment k8s-example-simple --port=8081 --target-port=8000 -n demo  (групировка подов под конкретный айпи)
kubectl get all -A -n demo 		
kubectl delete svc/k8s-example-simple -n demo

По-умолчанию в контейнер пробрасываются  сетервые переменнве окружения (порт, апи и прочее) всех подов, которые находятся в одном нейспейсе


Ноды:
kubectl get nodes
kubectl describe no/docker-desktop


Рксурсы:
kubectl get ns/demo
kubectl describe ns/demo