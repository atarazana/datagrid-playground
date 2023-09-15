
run-infinispan-podman-workaround: # I am using an old and bugged podman-compose version, temporal workaround
	podman run -it --rm \
	--net host \
	--name infinispan-srv1 \
	-e USER=admin -e PASS=admin \
	quay.io/infinispan/server:14.0

stop-infinispan-podman:
	podman-compose -f podman/container-compose.yaml down

run-infinispan-podman:
	podman-compose -f podman/container-compose.yaml up

infinispan-podman: run-infinispan-podman
