dataSource.url='jdbc:postgresql://postgresql:5432/docker'
dataSource.username='docker'
dataSource.password='docker'

grails.admin.client='info@cytomine.be'
grails.integration.aurora.url='http://localhost:8000/api/image/notify.json?test=true'
grails.integration.aurora.username='xxx'
grails.integration.aurora.password='xxx'
grails.integration.aurora.interval='60000'

cytomine.customUI.global = [
        dashboard: ["ALL"],
        search : ["ROLE_ADMIN"],
        project: ["ALL"],
        ontology: ["ROLE_ADMIN"],
        storage : ["ROLE_USER","ROLE_ADMIN"],
        activity : ["ALL"],
        feedback : ["ROLE_USER","ROLE_ADMIN"],
        explore : ["ROLE_USER","ROLE_ADMIN"],
        admin : ["ROLE_ADMIN"],
        help : ["ALL"]
]


grails.serverURL='http://$CORE_URL'
grails.imageServerURL=['http://$IMS_URL1','http://$IMS_URL2']
grails.retrievalServerURL =['http://$RETRIEVAL_URL']
grails.uploadURL='http://$UPLOAD_URL'

storage_buffer='$IMS_BUFFER_PATH'
storage_path='$IMS_STORAGE_PATH'

grails.retrievalPassword = '$RETRIEVAL_PASSWD'
grails.adminPassword='$ADMIN_PWD'
grails.adminPrivateKey='$ADMIN_PRIV_KEY'
grails.adminPublicKey='$ADMIN_PUB_KEY'
grails.superAdminPrivateKey='$SUPERADMIN_PRIV_KEY'
grails.superAdminPublicKey='$SUPERADMIN_PUB_KEY'
grails.ImageServerPrivateKey='$IMS_PRIV_KEY'
grails.ImageServerPublicKey='$IMS_PUB_KEY'
grails.rabbitMQPrivateKey='$RABBITMQ_PRIV_KEY'
grails.rabbitMQPublicKey='$RABBITMQ_PUB_KEY'

grails.notification.email='$SENDER_EMAIL'
grails.notification.password='$SENDER_EMAIL_PASS'
grails.notification.smtp.host='$SENDER_EMAIL_SMTP_HOST'
grails.notification.smtp.port='$SENDER_EMAIL_SMTP_PORT'


grails.mongo.host = 'mongodb'
grails.mongo.options.connectionsPerHost=10
grails.mongo.options.threadsAllowedToBlockForConnectionMultiplier=5

grails.retrievalUsername = 'cytomine'

grails.messageBrokerServerURL='rabbitmq:5672'

grails.serverID='$SERVER_ID'