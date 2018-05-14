#########
# Tasks #
#########

# Build project
#
#   make build
#
build: build/libs/package


build/libs/package: build/client build/demo
	make docker/image/client name=client version=1.0
	make docker/image/demo name=demo version=1.0

build/client:
	cd client-demo && ./mvnw clean install
build/demo:
	cd demofj36 && ./mvnw clean install

up:
	docker-compose up -d
	sleep 10
down:
	docker-compose stop && docker-compose rm -vf

# Build client-demo image to the given `name` and `version`
#
#   make docker/image/client name=client version=1.0.0
#
docker/image/client: build/client
	docker build -t $(name):$(version) ./client-demo/

# Build demo image to the given `name` and `version`
#
#   make docker/image/demo name=demo version=1.0.0
#
docker/image/demo: build/demo
	docker build -t $(name):$(version) ./demofj36/
