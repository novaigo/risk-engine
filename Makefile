# Makefile for risk-engine
# info: https://www.gnu.org/software/make/manual/
# usage: make help

# --------------------------
# Variables
# --------------------------
PROFILE ?= dev
JAR_NAME = target/risk-engine-0.0.1-SNAPSHOT.jar
SPRING_OPTS = --spring.profiles.active=$(PROFILE)

default: run-dev

build:
	mvn clean package

clean:
	mvn clean
run:
	java -jar $(JAR_NAME) $(SPRING_OPTS)

run-dev:
	mvn spring-boot:run -D spring-boot.run.profiles=$(PROFILE)

test:
	mvn test
rebuild: clean build

dev: rebuild run-dev

help:
	@echo "Makefile targets for risk-engine:"
	@echo "  build      - clean & build jar"
	@echo "  run        - run jar with active profile"
	@echo "  run-dev    - run Spring Boot in dev profile"
	@echo "  test       - run tests"
	@echo "  clean      - clean target"
	@echo "  rebuild    - clean & build"
	@echo "  dev        - rebuild & run dev"
