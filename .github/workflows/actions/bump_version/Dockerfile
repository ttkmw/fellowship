FROM alpine

ADD bump_version.sh /bump_version.sh
RUN chmod +x /bump_version.sh
RUN apk update && apk add git
ENTRYPOINT ["/bump_version.sh"]
