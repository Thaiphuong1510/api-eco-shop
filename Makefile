.PHONY: down build up clean help deploy

help:
	@echo Available targets:
	@echo make pull	   - pull new code
	@echo make down    - Stop and remove containers (including volumes)
	@echo make build   - Build Docker images for the application and MySQL
	@echo make up      - Start the application in detached mode
	@echo make clean   - Remove unused Docker images
	@echo make deploy  - Build and start the application
	@echo make help    - Display this help message

pull:
	git pull

down:
	docker-compose down -v || true

build:
	docker build -f dockerfile -t api-eco-shop:1.1 .
	docker build -f dockerfile-sql -t mysql:tz .

up:
	docker-compose up -d

clean:
	docker rmi $(docker images -f "dangling=true" -q) -f || true

deploy: down pull build up clean