FROM ghcr.io/graalvm/native-image-community:25 AS builder

WORKDIR /build

# Copy project
COPY . .

# Build native image
RUN ./mvnw -Pnative native:compile -DskipTests=true

# -------- Stage 2: Runtime --------
FROM gcr.io/distroless/base

WORKDIR /app

COPY --from=builder /build/target/control /app/control

EXPOSE 8893

ENTRYPOINT ["/app/control"]