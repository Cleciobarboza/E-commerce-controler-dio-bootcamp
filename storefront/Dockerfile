# Use a valid Gradle image with a specific JDK version
FROM gradle:8.7-jdk17

# Update the package list and install necessary packages
# Correct the typo from 'intall' to 'install'
# Use '-y' to automatically approve installations and '--no-install-recommends' for a smaller image
# Correct the typo in '--noinstall-recommends'
RUN apt-get update && apt-get install -y --no-install-recommends

# Set the environment variable for the installation path
# There should be no slash at the end of the variable name
ENV INSTALL_PATH /storefront

# Create the directory specified by the environment variable
# Use the full path here to ensure the directory is created correctly
RUN mkdir -p $INSTALL_PATH

# Set the working directory for subsequent commands
WORKDIR $INSTALL_PATH

# Copy the local files into the container's working directory
COPY . .