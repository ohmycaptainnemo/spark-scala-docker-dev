FROM mcr.microsoft.com/devcontainers/java:1-17-bookworm

ARG SPARK_VERSION=3.5.1
ARG HADOOP_VERSION=3

# Fix: Remove the Yarn repository to prevent GPG key errors during apt-get update
RUN rm -f /etc/apt/sources.list.d/yarn.list

RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install --no-install-recommends maven curl tar bash \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

RUN curl -O https://archive.apache.org/dist/spark/spark-${SPARK_VERSION}/spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz \
    && tar xzf spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz -C /opt/ \
    && rm spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz \
    && ln -s /opt/spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION} /opt/spark

ENV SPARK_HOME=/opt/spark
ENV PATH=$PATH:$SPARK_HOME/bin:$SPARK_HOME/sbin

# Switch to the default non-root user provided by the dev container
USER vscode

RUN curl -fL https://github.com/coursier/launchers/raw/master/cs-x86_64-pc-linux.gz | gzip -d > /home/vscode/cs \
    && chmod +x /home/vscode/cs \
    && /home/vscode/cs setup -y \
    && rm /home/vscode/cs

ENV PATH="$PATH:/home/vscode/.local/share/coursier/bin"